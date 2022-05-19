package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import BBDD.Db;
import BBDD.DbCiudad;
import Code.Billete;
import Code.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class BilletesClass extends MainInfinityClass {
	
	@FXML private Text numBilletes;
	@FXML private Text txtPrecio;
	@FXML private ComboBox<String> cbOrigen;
	@FXML private ComboBox<String> cbDestino;
	@FXML private DatePicker dpIda;
	@FXML private DatePicker dpVuelta;
	@FXML private Button btnSoloIda;
	@FXML private Button btnVuelta;
	@FXML private Button masBilletes;
	@FXML private Button menosBilletes;
	@FXML private Button btnAddCarrito;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	private static Db db = new Db();
	private static DbCiudad dbc = new DbCiudad();
	private static boolean idayvueltaboolean;
	////////////////////////METODOS////////////////////////
	
	public void reservaBilletes(MouseEvent event) { 
		Cliente cliente=db.selectCliente(Db.getUserConnected()); 
		int numBilletes= getNumBilletes();
		
		if(checkCamposVacios()) { //chekeamos que los campos estan rellenos
			String origen=cbOrigen.getValue();
			String destino=cbDestino.getValue();
			LocalDate ida=dpIda.getValue();   //guardamos los datos recogidos en los campos
			if(idayvueltaboolean) { //si la vuelta esta seleccionada generamos el billete de ida y el billete de vuelta
				LocalDate vuelta=dpVuelta.getValue();
				for(int i=0; i<numBilletes ;i++) {  //bucle que genera billetes en funcion del número de billetes seleccionados
					Billete billeteIda=new Billete(origen, destino, ida);
					Billete billeteVuelta=new Billete(destino, origen, vuelta);
					cliente.addProducto(billeteIda); //introducimos los billetes en el arrayList de productos alojado en Cliente 
					cliente.addProducto(billeteVuelta);
					System.out.println(billeteIda);
					System.out.println(billeteVuelta);
				}
			}
			else {
				for(int i=0; i<numBilletes ;i++) {//bucle que genera billetes en funcion del número de billetes seleccionados
					Billete billeteIda=new Billete(origen, destino, ida); //si la vuelta no esta seleccionada solo generamos billetes de ida
					cliente.addProducto(billeteIda);
					System.out.println(billeteIda);
					//añadir a la bbdd los billetes cuando la reserva se haya confirmado en el carrito
				}
			}
		}
		else {
			System.out.println("Algo salió mal...");
		}
	}
	
	
	//si los campos estan seleccionaddos y hay más de 0 billetes seleccionados returna true
	public boolean checkCamposVacios() {
		if(cbOrigen.getValue()!=null && cbDestino.getValue()!=null && dpIda.getValue()!=null && getNumBilletes()>0) { 
			return true;
		}
		else {
			return false;
		}
	}
	
	public void alterNumBilletes (MouseEvent event) {  //sumamos numero de billetes
		Object evt=event.getSource();
		int nb=getNumBilletes();
		if(evt.equals(masBilletes)) {
			nb++;
			setNumBilletes(nb);
			setTextPrecio();
		}
			
		else if (evt.equals(menosBilletes))
			nb--;
			if(nb>0) {
				setNumBilletes(nb);
				setTextPrecio();
			}
			else
				setNumBilletes(0);
	}
	
	public void setTextPrecio () {
		String precio="null";
			if(checkCamposVacios()) {
				for(int i=0; i<getNumBilletes();i++) {
					precio = String.valueOf(Billete.billete_getPrecio(cbOrigen.getValue(), cbDestino.getValue()) * getNumBilletes()) ;
					txtPrecio.setText(precio);
				}	
			}
			else {
				txtPrecio.setText("0");
			}
	}
	
	@FXML
	private void eventActionPrecio(ActionEvent event) {  
		Object evt=event.getSource();
		int nb=getNumBilletes();
		if(evt.equals(cbOrigen) || evt.equals(cbDestino) || evt.equals(dpIda)) {
			setTextPrecio();
		}
		else if(evt.equals(masBilletes)) {
			nb++;
			setNumBilletes(nb);
			setTextPrecio();
		}
			
		else if (evt.equals(menosBilletes))
			nb--;
			if(nb>0) {
				setNumBilletes(nb);
				setTextPrecio();
			}
			else
				setNumBilletes(0);
	}
	////////////////////////GETTERS & SETTERS////////////////////////

	public void setComboBox() {
		ArrayList<String> listaCiudades = dbc.listarCiudades();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			list.add(listaCiudades.get(i));
		}
		cbDestino.setItems(list);
		cbOrigen.setItems(list);
		
	}
	
	public int getNumBilletes () {
		int nb=Integer.parseInt(numBilletes.getText());
		return nb;
	}
	
	public void setNumBilletes (int nb) {
		numBilletes.setText(""+nb);
	}
	
	////////////////////////LOAD PAGES////////////////////////
	
	public void cargaIdaYvuelta(MouseEvent event) {
		loadPage("IdayvueltaPage");
		idayvueltaboolean=true;
	}
	public void cargaSoloIda(MouseEvent event) {
		loadPage("SoloIda");
		idayvueltaboolean=false;
	}
	
	private void loadPage(String page) { //metodo para cargar pagina
		Parent root=null;

		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}
	
	@SuppressWarnings("unused")
	private void clear () {
		
	}
	
}

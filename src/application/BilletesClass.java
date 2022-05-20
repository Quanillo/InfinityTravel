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
	private static double precioBase;
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
		if(idayvueltaboolean && cbOrigen.getValue()!=null && cbDestino.getValue()!=null && dpIda.getValue()!=null && dpVuelta.getValue()!=null && getNumBilletes()>=1)  
			return true;
		
		else if(!idayvueltaboolean && cbOrigen.getValue()!=null && cbDestino.getValue()!=null && dpIda.getValue()!=null && getNumBilletes()>=1) 
			return true;
		else 
			return false;
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
		String sPrecio="null";
		double dPrecio = 0;
			if(checkCamposVacios()) {
				for(int i=0; i<getNumBilletes();i++) {
					dPrecio =precioBase* getNumBilletes() ;
				}
				if(idayvueltaboolean) {
					sPrecio=String.valueOf(dPrecio*2);
					txtPrecio.setText(sPrecio);
				}
				else {
					sPrecio=String.valueOf(dPrecio);
					txtPrecio.setText(sPrecio);
				}
			}
			else {
				txtPrecio.setText("0");
			}
	}
	
	@FXML
	public void eventActiongetPrecioBilleteBase (ActionEvent event) {
		Object evt=event.getSource();
		double precio=0;
		if(evt.equals(cbOrigen) || evt.equals(cbDestino) || evt.equals(dpIda) || evt.equals(dpVuelta) && checkCamposVacios()){
			precio=(Billete.billete_getPrecio(cbOrigen.getValue(), cbDestino.getValue())) ;
			setTextPrecio();
		}
		precioBase=precio;
	}
	
	@FXML
	private void precioSumaResta(ActionEvent event) {  
		Object evt=event.getSource();
		int nb=getNumBilletes();
		if(evt.equals(masBilletes)) {
			nb++;
			setNumBilletes(nb);
			setTextPrecio();
		}	
		else if (evt.equals(menosBilletes)) {
			nb--;
			if(nb>0) {
				setNumBilletes(nb);
				setTextPrecio();
			}
			else
				setNumBilletes(0);
		}
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
		if(nb<1) 
			numBilletes.setText("1");
		else
			numBilletes.setText(""+ nb);
	}
	
	public double getPrecioBilletes () {
		double nb=Integer.parseInt(txtPrecio.getText());
		return nb;
	}
	
	public void setPrecioBilletes (double pb) {
		txtPrecio.setText(""+pb);
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

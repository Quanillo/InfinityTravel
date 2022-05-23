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

public class BilletesClass extends MainInfinityClass{
	
	@FXML private Text txtNumBilletes;
	@FXML private Text txtPrecio;
	@FXML private ComboBox<String> cbOrigen;
	@FXML private ComboBox<String> cbDestino;
	@FXML private DatePicker dpIda;
	@FXML private DatePicker dpVuelta;
	@FXML private Button btnSoloIda;
	@FXML private Button btnVuelta;
	@FXML private Button btnMasBilletes;
	@FXML private Button btnMenosBilletes;
	@FXML private Button btnAddCarrito;
	@FXML private Button btnCambiaCiudad;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	//private static Db db = new Db();
	private static DbCiudad dbc = new DbCiudad();
	private static boolean idayvueltaboolean;
	private static double precioBase;
	////////////////////////METODOS////////////////////////
	
	public void reservaBilletes(MouseEvent event) { 
		//Cliente cliente=db.selectCliente(Db.getUsername()); 
		Cliente cliente=Db.getUserConnected();
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
		else {//este else creo que se puede eliminar
			System.out.println("Algo salió mal...");
		}

		clear();
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

	//para cambiar el orden de las ciudades. trolea todavía no cambia la segunda ciudad...revisar
	public void cambiaCiudad() {
		String destino=cbDestino.getValue();
		String origen=cbOrigen.getValue();
		cbDestino.setValue(null);
		cbOrigen.setValue(null);
		setComboBoxOrigen();
		setComboBoxDestino();
		cbDestino.setValue(origen);
		cbOrigen.setValue(destino);
		
	}
	//metodo que calcula el precio del billete base cada vez que se cambian las cuidades de origen y destino estando ambas completas
	@FXML
	public void eventActiongetPrecioBilleteBase (ActionEvent event) {
		Object evt=event.getSource();
		double precio=0;
		if(evt.equals(cbOrigen) || evt.equals(cbDestino) || evt.equals(dpIda) || evt.equals(dpVuelta) && checkCamposVacios()){
			precio=(Billete.billete_getPrecio(cbOrigen.getValue(), cbDestino.getValue())) ;
			txtPrecio.setText("0.00");
			setTextPrecio();
		}
		precioBase=precio;
	}
	//metodo que suma el numero de billetes segun va dandole al boton de masBilletes o menosBilletes y actualiza el precio si los campos estan completos
	@FXML
	private void precioSumaResta(ActionEvent event) {  
		Object evt=event.getSource();
		int nb=getNumBilletes();
		if(evt.equals(btnMasBilletes)) {
			nb++;
			setNumBilletes(nb);
			setTextPrecio();
		}	
		else if (evt.equals(btnMenosBilletes)) {
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
	//metodo que rellena el combobox de origen, sin incluir la ciudad seleccionada en el destino 
	public void setComboBoxOrigen() {
		ArrayList<String> listaCiudades = dbc.listarCiudades();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			if(cbDestino.getValue()==null) {
				list.add(listaCiudades.get(i));
			}
			else if(!cbDestino.getValue().equals(listaCiudades.get(i))) {
				list.add(listaCiudades.get(i));
			}
		}
		cbOrigen.setItems(list);
		
	}
	//metodo que rellena el combobox de destino, sin incluir la ciudad seleccionada en el origen
	public void setComboBoxDestino() {
		ArrayList<String> listaCiudades = dbc.listarCiudades();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			if(cbOrigen.getValue()==null) {
				list.add(listaCiudades.get(i));
			}
			else if(!cbOrigen.getValue().equals(listaCiudades.get(i))) {
				list.add(listaCiudades.get(i));
			}
		}
		cbDestino.setItems(list);
	}
	
	
	
	public int getNumBilletes () {
		int nb=Integer.parseInt(txtNumBilletes.getText());
		return nb;
	}
	
	public void setNumBilletes (int nb) {
		if(nb<1) 
			txtNumBilletes.setText("1");
		else
			txtNumBilletes.setText(""+ nb);
	}
	
	public double getPrecioBilletes () {
		double pb=Integer.parseInt(txtPrecio.getText());
		return pb;
	}
	//setTextPrecio() y setPrecioBilletes se pueden fusionar.
	public void setTextPrecio () {
		double dPrecio = 0;
			if(checkCamposVacios()) {
				for(int i=0; i<getNumBilletes();i++) {
					dPrecio =precioBase* getNumBilletes() ;
				}
				if(idayvueltaboolean) {
					setPrecioBilletes(dPrecio);
				}
				else {
					setPrecioBilletes(dPrecio);
				}
			}
			else {
				txtPrecio.setText("0");
			}
	}
	
	public void setPrecioBilletes (double pb) {
		double precio = Math.round(pb*100.0)/100.0;
		txtPrecio.setText(""+precio);
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
	
	private void clear () {
		txtNumBilletes.setText("1");
		txtPrecio.setText("0.00");
		cbOrigen.setValue(null);
		cbDestino.setValue(null);
		dpIda.setValue(null);
		if(idayvueltaboolean)
			dpVuelta.setValue(null);
	}

}

/*public void alterNumBilletes (MouseEvent event) {  //sumamos numero de billetes
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
}*/

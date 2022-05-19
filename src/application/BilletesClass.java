package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import BBDD.Db;
import Code.Billete;
import Code.Cliente;
import Code.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class BilletesClass extends MainInfinityClass {
	
	@FXML private Text numBilletes;
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
	
	////////////////////////METODOS////////////////////////
	
	public void reservaBilletes(MouseEvent event) {  //este metodo esta fallando porque no recoge los campos ya que el boton de reserva billetes (el de añadir al carrito) no se encuentra en la misma pagina que ellos. 
													//Por otro lado hay que plantear como chekear que el campo de vuelta no esta seleccionado dado que tambien peta al intentar conseguir su valor (.getValue)
		
		LocalDate fechaReserva=LocalDate.now(); //guardamos la fecha actual para guardarla en la reserva
		Cliente cliente=db.selectCliente(Db.getUserConnected()); //creamos un cliente identificando al usuario actualmente conectado para crear una reserva y añadirla al array de reservas de dicho cliente (ver metodo)
		Reserva reserva=new Reserva (cliente, fechaReserva); //generamos una reserva (ojo, falta un metodo para generar el id de la reserva)	
		int numBilletes= 1;//getNumBilletes();
		
		if(checkCamposVacios()) { //chekeamos que los campos estan rellenos
			String origen=cbOrigen.getValue();
			String destino=cbDestino.getValue();
			LocalDate ida=dpIda.getValue();
			//guardamos los datos recogidos en los campos
			if(dpVuelta.getValue()!=null) { //si la vuelta esta seleccionada generamos el billete de ida y el billete de vuelta
				LocalDate vuelta=dpVuelta.getValue();
				for(int i=0; i<numBilletes ;i++) {  //bucle que genera billetes en funcion del número de billetes seleccionados
					Billete billeteIda=new Billete(origen, destino, ida);
					Billete billeteVuelta=new Billete(destino, origen, vuelta);
					reserva.addProducto(billeteIda);
					reserva.addProducto(billeteVuelta); 
					//añadir a la bbdd los billetes cuando la reserva se haya confirmado en el carrito
				}
			}
			else {
				for(int i=0; i<numBilletes ;i++) {//bucle que genera billetes en funcion del número de billetes seleccionados
					Billete billete=new Billete(origen, destino, ida); //si la vuelta no esta seleccionada solo generamos billetes de ida
					reserva.addProducto(billete);
					//añadir a la bbdd los billetes cuando la reserva se haya confirmado en el carrito
				}
			}
		}
		else {
			System.out.println("Algo salió mal...");
		}
	}
	
	public void campos(MouseEvent event) {
		System.out.println(cbOrigen.getValue() + cbDestino.getValue() + dpIda.getValue() + getNumBilletes());
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
		}
			
		else if (evt.equals(menosBilletes))
			nb--;
			if(nb>0)
				setNumBilletes(nb);
			else
				setNumBilletes(0);
	}
	
	
	////////////////////////GETTERS & SETTERS////////////////////////
	

	public void setComboBox() {
		ArrayList<String> listaCiudades = db.listarCiudades();
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
	}
	public void cargaSoloIda(MouseEvent event) {
		loadPage("SoloIda");
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
	
}

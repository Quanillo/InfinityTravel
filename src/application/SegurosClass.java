package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import BBDD.Db;
import BBDD.DbProducto;
import Code.Cliente;
import Code.Seguro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SegurosClass extends MainInfinityClass{
	@FXML private DatePicker dpInicio;
	@FXML private DatePicker dpFin;
	@FXML private Button btnEco;
	@FXML private Button btnLux;
	@FXML private Button btnPro;
	@FXML private Button btnAddCarrito;
	@FXML private TextFlow tf;
	@FXML private Text txtSeguro;
	@FXML private Text txtDescripcion;
	@FXML private Text txtPrecio;
	private static DbProducto dbp = new DbProducto();
	private Seguro seguroSeleccionado;
	
	
	public void reservaSeguro (MouseEvent event) {
		if(checkCamposVacios()) { //chekeamos que los campos estan rellenos
			Cliente cliente=Db.getUserConnected();
			LocalDate entrada=dpInicio.getValue();   //guardamos los datos recogidos en los campos
			LocalDate salida=dpFin.getValue();
			double precioFinal=getPrecio();
			Seguro segAux=seguroSeleccionado;
			segAux.setIncio(entrada);
			segAux.setFin(salida);
			segAux.setImporteProducto(precioFinal);
			cliente.addProducto(segAux);
		}
		else {//este else creo que se puede eliminar
			System.out.println("Algo salió mal...");
		}
		clear();
	}
	
	@FXML
	private void eventActionInfoSeguro(ActionEvent event) {  
		Object evt=event.getSource();
		double precio=0;
		if(evt.equals(btnEco)) {
			seguroSeleccionado=dbp.getSeguro("Economic");
			txtDescripcion.setText(Seguro.descripcionEco());
			txtSeguro.setText("Economic");
			precio=getPrecio();
		}	
		else if (evt.equals(btnLux)) {
			seguroSeleccionado=dbp.getSeguro("Luxury");
			txtDescripcion.setText(Seguro.descripcionLux());
			txtSeguro.setText("Luxury");
			precio=getPrecio();
		}
		else if (evt.equals(btnPro)) {
			seguroSeleccionado=dbp.getSeguro("Pro");
			txtDescripcion.setText(Seguro.descripcionPro());
			txtSeguro.setText("Pro");
			precio=getPrecio();
		}
		else if(evt.equals(dpInicio) || evt.equals(dpFin)) {
			precio=getPrecio();
		}
		txtPrecio.setText(""+precio);
	}
	
	private double getPrecio() {
		double precio=0;
		if(checkCamposVacios())
			precio= seguroSeleccionado.getImporteProducto()*getNumDias();
		else {
			if (seguroSeleccionado!=null)
				precio= seguroSeleccionado.getImporteProducto();
		}
		return  Math.round(precio*100.0)/100.0;
	}
	
	private int  getNumDias () {
		int dias = 0;
		if(dpInicio.getValue()!=null && dpFin.getValue()!=null) {
			LocalDate entrada=dpInicio.getValue();
			LocalDate salida=dpFin.getValue();
			dias = (int) ChronoUnit.DAYS.between(entrada, salida);
		}
		return dias;
	}
	
	public boolean checkCamposVacios() {
		if(dpInicio.getValue()!=null && dpFin.getValue()!=null && getNumDias()>=1 && seguroSeleccionado!=null) 
			return true;
		else 
			return false;
	}

	public void clear() {
		txtPrecio.setText("0");
		txtSeguro.setText(null);
		txtDescripcion.setText(null);
		dpInicio.setValue(null);
		dpFin.setValue(null);
	}
	
}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import Code.Cliente;
import db.Db;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Clase controladora de la ventana personal del usuario.
 */
public class UserClass implements Initializable {

	@FXML private Text txtValidado;
	@FXML private TextField txtNombre;
	@FXML private TextField txtApellidos;
	@FXML private TextField txtDni;
	@FXML private TextField txtTelefono;
	@FXML private DatePicker dpNacimiento;
	@FXML private Button btnEnviar;
	private Db db=new Db();
	/**
	 * Introduce los datos introducidos por el cliente introduciendolos en la bbdd
	 * @param event  Evento de rat�n (el usuario pulsa el bot�n).
	 */
	public void setDatos(MouseEvent event) {
		Cliente user=Db.getUserConnected();
		if(!txtNombre.getText().trim().isEmpty() && !txtApellidos.getText().trim().isEmpty() && !txtDni.getText().trim().isEmpty() &&
			!txtTelefono.getText().trim().isEmpty() && dpNacimiento.getValue()!=null) {
			user.setNombre(txtNombre.getText());
			user.setApellidos(txtApellidos.getText());
			user.setDni(txtDni.getText());
			user.setTelefono(Integer.parseInt(txtTelefono.getText()));
			user.setfNacimiento(dpNacimiento.getValue());
			db.updateCliente(user);
			user.setClienteValidado(true);
			txtValidado.setText("Usuario validado");
		}
		clear();
	}
	/**
	 * Consulta en la bbdd si el usuario a introducido los datos solicitados necesarios para realizar la compra
	 */
	public void usuarioValidado () {
		Cliente user=Db.getUserConnected();
		if(user.isClienteValidado()) 
			txtValidado.setText("Usuario validado");
		else
			txtValidado.setText("Usuario no validado");
	}
	/**
	 * Deja los campos de la interface con los valores de inicio.
	 */
	public void clear(){
		txtNombre.clear();
		txtApellidos.clear();
		txtDni.clear();
		txtTelefono.clear();
		dpNacimiento.setValue(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usuarioValidado();
		
	}
	
	
}
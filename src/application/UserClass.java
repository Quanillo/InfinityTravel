package application;

import BBDD.Db;
import Code.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserClass {

	@FXML private TextField txtNombre;
	@FXML private TextField txtApellidos;
	@FXML private TextField txtDni;
	@FXML private TextField txtTelefono;
	@FXML private DatePicker dpNacimiento;
	@FXML private Button btnEnviar;
	
	public void setDatos(MouseEvent event) {
		if(!txtNombre.getText().trim().isEmpty() && !txtApellidos.getText().trim().isEmpty() && !txtDni.getText().trim().isEmpty() &&
			!txtTelefono.getText().trim().isEmpty() && dpNacimiento.getValue()!=null) {
			Cliente user=Db.getUserConnected();
			user.setNombre(txtNombre.getText());
			user.setApellidos(txtApellidos.getText());
			user.setDni(txtDni.getText());
			user.setTelefono(Integer.parseInt(txtTelefono.getText()));
			user.setfNacimiento(dpNacimiento.getValue());
			//Db.updateCliente(user);
			user.setClienteValidado(true);
		}
		clear();
	}
	
	
	public void clear(){
		txtNombre.clear();
		txtApellidos.clear();
		txtDni.clear();
		txtTelefono.clear();
		dpNacimiento.setValue(null);
	}
}

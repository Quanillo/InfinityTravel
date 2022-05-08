package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LoginClass {

	@FXML private BorderPane bp;
	@FXML private TextField txtUser;
	@FXML private PasswordField txtPass;
	@FXML private Button btnLogin;
	@FXML private Button btnReg;
	@FXML private Text txt;
	
	@FXML
	private void eventKey(KeyEvent event) {
		Object evt=event.getSource();
		
		if(evt.equals(txtUser)) {
			 txtUser.addEventFilter(KeyEvent.ANY, evento -> {
			        if (evento.getCharacter().matches(" "))
			            evento.consume();
			 });
		}
		else if(evt.equals(txtPass)) {
			 txtPass.addEventFilter(KeyEvent.ANY, evento -> {
			        if (evento.getCharacter().matches(" "))
			            evento.consume();
			 });
		}
	}
	
	@FXML
	private void eventAction(ActionEvent event) {
		Object evt=event.getSource();
		
		if(evt.equals(btnLogin)) {
			if(!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty()) {
				String user=txtUser.getText();
				String pass=txtPass.getText();
				int state=1;//Aqui habría que buscar en la bbdd si el usuario y la contraseña estan guardados y que el metodo devuelva 1 si estan, 0 si no esta y -1 si se produjo algun error
				//este if es para probar la interface, el valor del state se debe conseguir con el metodo indicado en el comentario de la linea de arriba.
				if(user.equalsIgnoreCase("Admin") && pass.equals("pass")) {
					state=1;
				}
				else {
					state=0;
				}
				
				if(state==1) {
					loadPage("MainInfinity");
				}
				else {
					txt.setText("Datos de acceso incorrectos");
					clear();
				}
				
			}else {
				txt.setText("Introduzca Usuario y Contraseña por favor");
				clear();
			}
		}
	}
	
	public void registro(ActionEvent event) {
		loadPage("RegistroPage");
	}
	
	private void loadPage(String page) {
		Parent root=null;
	
		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}
	
	public void clear () {
		txtUser.clear();
		txtPass.clear();
	}
}

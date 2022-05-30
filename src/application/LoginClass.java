package application;

import java.io.IOException;

import db.Db;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * Clase controladora de la ventana de inicio de sesion.
 */
public class LoginClass {

	@FXML private BorderPane bp;
	@FXML private TextField txtUser;
	@FXML private PasswordField txtPass;
	@FXML private Button btnLogin;
	@FXML private Button btnReg;
	@FXML private Button exit;
	@FXML private Text txt;
	private static Db db = new Db();
	/**
	 * Impide que el usuario pueda introducir espacios en los campos de texto.
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
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
			        else if (evento.getCode().equals(KeyCode.ENTER)) 
			            login();
			 });
		}
	}
	/**
	 * Lanza el método login cuando el botón es pulsado
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
	@FXML
	private void eventAction(ActionEvent event) {
		Object evt=event.getSource();
		if(evt.equals(btnLogin)) {
			login();
		}
	}
	/**
	 * Válida que el usuario y la contraseña son válidos comprobandolo en la bbdd y diríge a la pagina principal dentro de la aplicación.
	 * Si los datos son incorrectos informa al usuario inmprimiendo en la interface los motivos.
	 */
	public void login () {
		if(!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty()) {
			String user=txtUser.getText();
			String pass=txtPass.getText();
			boolean state;
			// buscamosen el usuario y la contraseña estan guardados y devuelve un boolean si el usuario es valido o no se encuentra en la bbdd
			if(db.login_validUser(user, pass)) {  
				state=true;
			}
			else {
				state=false;
			}

			if(state==true) {
				loadPage("MainInfinity");
				Db.setUserConnected(user);
				//Db.setUsername(user);
			}
			else {
				txt.setText("Datos de acceso incorrectos.");
				clear();
			}

		}
		else {
			txt.setText("Introduzca Usuario y Contraseña por favor.");
			clear();
		}
	}
	/**
	 * Carga la pagina de registro si el usuario pincha el botón
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
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
	
	@FXML
	private void close(MouseEvent event) {
		Platform.exit();
	}
	
	public void clear () {
		txtUser.clear();
		txtPass.clear();
	}
}

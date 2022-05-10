package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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

public class RegistroClass {

	@FXML private BorderPane bp;
	@FXML private TextField txtUser;
	@FXML private PasswordField txtPass;
	@FXML private PasswordField txtPass2;
	@FXML private Button btnreg;
	@FXML private Button btnLog;
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
		
		if(evt.equals(btnreg)) {
			if(!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty() && !txtPass2.getText().isEmpty()) {
				String user=txtUser.getText();
				String pass=txtPass.getText();
				String pass2=txtPass2.getText();
				int state=1;
				
				if(user.length()>=4) {//comprobamos que el usuario tiene minimo 4 caracteres
					if(pass.equals(pass2)) {//comprobamos que las contraseñas coinciden
						if(pass.length()>=6) {//comprobamos que la contraseña tiene minimo 6 caracteres.
							//if(user----no esta en la base de datos)//aqui llamarimos a un metodo que devuelve boolean y comprobando que el usuario no este registrado en la bbdd
								//aqui llamariamos a un metodo para guardar el usuario y la contraseña en la bbdd
								state=1;
							//else
								//state=0;
								//txt.setText("Usuario registrado. Elija otro nombre de usuario porfavor.");
								//clear();
						}
						else {
							state=0;
							txt.setText("Contraseña inválida: debe tener al menos 6 caracteres");
							clear();
						}
					}
					else {
						state=0;
						txt.setText("Contraseña inválida: la confirmación no coincide");
						clear();
					}
				}
				else {
					state=0;
					txt.setText("El nombre de usuario debe tener al menos 4 caracteres");
					clear();
				}
				//comprobamos el valor del state para saber si accedemos a la app o no
				if(state==1) {
					loadPage("LoginPage");
				}
				else {
					clear();
				}
				
			}else {
				txt.setText("Registro inválido: Alguno de los campos esta vacio.");
				clear();
			}
		}
		if(evt.equals(btnLog)) {
			loadPage("LoginPage");
		}
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
		txtPass2.clear();
	}
	
	@FXML
	private void close(MouseEvent event) {
		Platform.exit();
	}
}
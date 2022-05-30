package application;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Code.Cliente;
import db.Db;
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

/**
 * Clase controladora de la ventana de registro.
 */
public class RegistroClass {

	@FXML private BorderPane bp;
	@FXML private TextField txtUser;
	@FXML private TextField txtCorreo;
	@FXML private PasswordField txtPass;
	@FXML private PasswordField txtPass2;
	@FXML private Button btnreg;
	@FXML private Button btnLog;
	@FXML private Text txt;
	private static Db db = new Db();
	/**
	 * metodo que impide agregar espacios en los campos de texto
	 * @param event
	 */
	@FXML
	private void eventKey(KeyEvent event) { 
		Object evt=event.getSource();
		if(evt.equals(txtUser) || evt.equals(txtPass) || evt.equals(txtPass2) || evt.equals(txtCorreo)) {
			txtUser.addEventFilter(KeyEvent.ANY, evento -> {
				if (evento.getCharacter().matches(" "))
					evento.consume();
			});
			txtPass.addEventFilter(KeyEvent.ANY, evento -> {
				if (evento.getCharacter().matches(" "))
					evento.consume();
			});
			txtPass2.addEventFilter(KeyEvent.ANY, evento -> {
				if (evento.getCharacter().matches(" "))
					evento.consume();
			});
			txtCorreo.addEventFilter(KeyEvent.ANY, evento -> {
				if (evento.getCharacter().matches(" "))
					evento.consume();
			});
		}
	}
	/**
	 * Con las debidas v�lidaciones este metodo da de alta en la bbdd al usuario, cargando la p�gina de login para que pueda entrar en la aplicaci�n
	 * @param event
	 */
	@FXML
	private void eventAction(ActionEvent event) {  
		Object evt=event.getSource();

		if(evt.equals(btnreg)) {
			if(checkCamposVacios()) {  //comprobamos que todos los campos contienen informacion, si checkCamposVacios devuelve true
				String user=txtUser.getText();
				String correo=txtCorreo.getText();
				String pass=txtPass.getText();
				String pass2=txtPass2.getText();
				boolean state=false;
				if(checkCamposValidos(user, pass, pass2, correo)) { //si checkCamposValidos nos devuelve true los campos son validos
					Cliente c = new Cliente (user, pass, correo);
					if(db.registrarCliente(c)) { //aqui llamariamos a un metodo para guardar el usuario y la contrase�a en la bbdd, si devuelve true es que se ha insertado con exito
						state=true;
					}
				}
				//comprobamos el valor del state para saber si accedemos a la app o no. Si es 1 el cliente se ha insertado.
				if(state==true) {
					loadPage("LoginPage");
				}
				else {
					clear();
				}
			}
		}		
		if(evt.equals(btnLog)) {
			loadPage("LoginPage");
		}
	}
	/**
	 * Valida que los campos estan completos.
	 * @return Devuelve true si los campos estan completos.
	 */
	public boolean checkCamposVacios () { //metodo que valida que todos los campos contienen informacion
		if(!txtUser.getText().isEmpty() && !txtCorreo.getText().isEmpty() && !txtPass.getText().isEmpty() && !txtPass2.getText().isEmpty()) {
			return true;
		}
		else {
			txt.setText("Registro inv�lido: Alguno de los campos esta vacio.");
			clear();
			return false;
		}
	}
	/**
	 * Comprueba que los campos cumplen con los requisitos establecidos. Si no los cumple, informa al usuario imprimiendo por pantalla el motivo
	 * @return Devuelve true si los campos estan completos.
	 */
	public boolean checkCamposValidos (String user, String pass, String pass2, String correo) {  //metodo que valida los campos de registro y si son validos inserta un cliente en la bbdd. devuelve 1 si es insertado
		boolean state=false;
		//comprobamos que el usuario tiene minimo 4 caracteres, el correo en el formato correcto, las 2 contrase�as iguales y mas de 6 caracteres y que el usuario no existe.
		if(user.length()>=4 && validaCorreo(correo) && pass.equals(pass2) && pass.length()>=6 && !db.register_usernameAlreadyExists(user)){ 
			state=true;
		}
		else {
			state=false;
			if(user.length()<4)
				txt.setText("El nombre de usuario debe tener al menos 4 caracteres.");
			else if(!validaCorreo(correo))
				txt.setText("Formato del correo electr�nico inv�lido.");
			else if(!pass.equals(pass2))
				txt.setText("Contrase�a inv�lida: la confirmaci�n no coincide.");
			else if(pass.length()<6)
				txt.setText("Contrase�a inv�lida: debe tener al menos 6 caracteres.");
			else if (db.register_usernameAlreadyExists(user))   
				txt.setText("Usuario registrado. Elija otro nombre de usuario porfavor.");
			
			clear();
		}
		return state;
	}
	/**
	 * Valida que el correo introducido es un correo electr�nico
	 * @param correo Correo que introduce el usuario pasado por par�metro
	 * @return
	 */
	public static boolean validaCorreo(String correo) { //metodo para validar el formato del correo
		Pattern CORREO_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = CORREO_REGEX.matcher(correo);
		return m.find();
	}
	
	/**
	 * M�todo gen�rico para cargar en la interface cada una ded las p�ginas.
	 * @param page Nombre de la p�gina que se quiere cargar.
	 */
	
	private void loadPage(String page) { //metodo para cargar pagina
		Parent root=null;

		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}
	/**
	 * Deja los campos de la interface con los valores de inicio.
	 */
	public void clear () {  //metodo para limpiar los campos
		txtUser.clear();
		txtPass.clear();
		txtPass2.clear();
	}
	/**
	 * Cierra la aplicaci�n
	 */
	@FXML
	private void close(MouseEvent event) { //metodo para cerrar el programa
		Platform.exit();
	}

}
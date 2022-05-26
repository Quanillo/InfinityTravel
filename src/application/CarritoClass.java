package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import BBDD.Db;
import BBDD.DbProducto;
import Code.Cliente;
import Code.Producto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CarritoClass extends  MainInfinityClass implements Initializable{

	@FXML private TextFlow txtListado;
	@FXML private Text txtCarrito;
	@FXML private Button btnComprar;
	@FXML private ScrollPane scPane;
	DbProducto dbp=new DbProducto();

	public void setTextCarrito() {
		Cliente cliente=Db.getUserConnected();
		ArrayList<Producto> listaProductos =cliente.getCarrito();
		String carrito="";
		if(listaProductos.isEmpty()) {
			carrito="No hay productos en el carrito";
		}
		else {
			for(int i=0; i<listaProductos.size(); i++) {
				carrito+=listaProductos.get(i).toString();

			}
		}
		txtCarrito.setText(carrito);
	}

	public void comprar(MouseEvent event) {
		Cliente cliente=Db.getUserConnected();
		ArrayList<Producto> listaProductos =cliente.getCarrito();
		String carrito="";double total = 0;
		for(int i=0; i<listaProductos.size(); i++) {
			carrito+=listaProductos.get(i).toString();
			total=listaProductos.get(i).getImporteProducto();
		}
		carrito+="\nImporte final: " + total;
		dbp.checkout(listaProductos);
		Db.getUserConnected().getCarrito().removeAll(listaProductos);
		mandarFactura(carrito);
		finish();
	}

	public void mandarFactura (String body) {
		String to = "ppetersondaw@gmail.com";
		String from = "infinitytravelnoreply@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("infinitytravelnoreply@gmail.com", "Ullman10");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Factura");
			message.setText(body);

			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTextCarrito();
	}
	public void finish() {
		txtCarrito.setText("Compra realizada con éxito! \n :)");

	}
}

//------Esto es un ejemplo de como se inicializa un Text al abrir la pagina implementando Initializable
 
/*public void setTextCarrito() {
Cliente cliente=Db.getUserConnected();
ArrayList<Producto> listaProductos =cliente.getCarrito();
String carrito="";
if(listaProductos.isEmpty()) {
	carrito="No hay productos en el carrito";
}
else {
	for(int i=0; i<listaProductos.size(); i++) {
		carrito+=listaProductos.get(i).toString();
	}
}
txtCarrito.setText(carrito);
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
setTextCarrito();
}*/
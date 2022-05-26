package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
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
			total+=listaProductos.get(i).getImporteProducto();
		}
		carrito+="\nImporte final: " + total;
		dbp.checkout(listaProductos);
		Db.getUserConnected().getCarrito().removeAll(listaProductos);
		enviarMail("ppetersondaw@gmail.com", "Factura", carrito);
		finish();
	}

	
	private static void enviarMail(String destinatario, String asunto, String cuerpo) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "infinitytravelnoreply";  //Para la dirección nomcuenta@gmail.com
	    String clave = "Ullman10";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Ullman10");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	 
	    
	    
	    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
		props.setProperty("mail.smtp.port", "465"); 
		props.setProperty("mail.smtp.socketFactory.port", "465"); 
	    

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
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
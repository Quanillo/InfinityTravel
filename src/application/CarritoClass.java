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

import Code.Cliente;
import Code.Producto;
import db.Db;
import db.DbProducto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Clase controladora de la ventana del carrito.
 */
public class CarritoClass implements Initializable{

	@FXML private TextFlow txtListado;
	@FXML private Text txtCarrito;
	@FXML private Button btnComprar;
	@FXML private ScrollPane scPane;
	DbProducto dbp=new DbProducto();
	/**
	 * Imprime el listado del ArrayList carrito alojado en el objeto del cliente conectado.
	 */
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
	/**
	 * Realiza la compra de los productos alojados en el carrito, y env�a la factura al correo del cliente utilizando su m�todo correspondiente
	 * @param event Evento de rat�n (el usuario pulsa el bot�n).
	 */
	public void comprar(MouseEvent event) {
		Cliente cliente=Db.getUserConnected();
		if(cliente.isClienteValidado()) {
			ArrayList<Producto> listaProductos =cliente.getCarrito();
			String carrito="Reserva a nombre de: " + cliente.getNombre() +" " + cliente.getApellidos()+ "\n\n"
					+ "Detalles de la reserva: \n\n";
			double total = 0;
			for(int i=0; i<listaProductos.size(); i++) {
				carrito+=listaProductos.get(i).toString();
				total+=listaProductos.get(i).getImporteProducto();
			}
			carrito+="\n\nImporte final: \n" + Math.round(total*100.0)/100.0 +"�";
			dbp.checkout(listaProductos);
			Db.getUserConnected().getCarrito().removeAll(listaProductos);
			enviarMail(cliente.getCorreo(), "Factura", carrito);
			finish();
		}
		else {
			txtCarrito.setText("Cliente no v�lidado. Dirijase a la zona de Usuario para completar la informaci�n necesaria para realizar la factura.");
		}
	}
	
	/**
	 * Env�a un mail al correo electronico componiendo el mensaje con su correo, el asunto y el cuerpo.
	 * @param destinatario
	 * @param asunto
	 * @param cuerpo
	 */
	private static void enviarMail(String destinatario, String asunto, String cuerpo) {
	    String remitente = "infinitytravelnoreply"; 
	    String clave = "Ullman10";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com"); 
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Ullman10");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.starttls.enable", "true");
	 
	    
	    
	    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
		props.setProperty("mail.smtp.port", "465"); 
		props.setProperty("mail.smtp.socketFactory.port", "465"); 
	    

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   
	    }
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTextCarrito();
	}
	
	public void finish() {
		txtCarrito.setText("Compra realizada con �xito! \n :)");

	}	
}

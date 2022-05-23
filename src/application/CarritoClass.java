package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class CarritoClass implements Initializable{

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
		dbp.checkout(listaProductos);
	}
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		setTextCarrito();
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
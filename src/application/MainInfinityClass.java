package application;

import java.io.IOException;
import java.util.ArrayList;

import BBDD.Db;
import Code.Cliente;
import Code.Producto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainInfinityClass {

	@FXML private Pane p;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	@FXML private Button packs;
	@FXML private Button Billetes;
	@FXML private Button Alojamiento;
	@FXML private Button Vehiculos;
	@FXML private Button Experiencias;
	@FXML private Button Seguros;
	@FXML private Button exit;
	//------Atributos del carrito------
	@FXML private AnchorPane apCarrito;
	@FXML private Button btnVerCarrito;
	@FXML private Text txtPrecioFinal;
	@FXML private Text txtCarrito;
	@FXML private TextFlow txtCarritoFlow;
	private long c=1; //contador para mostrar y esconder el carrito
	
	@FXML 
	private void packsLoad(MouseEvent event) {
		loadPage("PacksPage");
	}
	
	@FXML 
	private void billetesLoad(MouseEvent event) {
		loadPage("BilletesPage");
	}
	
	@FXML 
	private void alojamientoLoad(MouseEvent event) {
		loadPage("AlojamientoPage");
	}
	
	@FXML 
	private void vehiculosLoad(MouseEvent event) {
		loadPage("VehiculosPage");
	}
	
	@FXML 
	private void experienciasLoad(MouseEvent event) {
		loadPage("ExperienciasPage");
	}
	
	@FXML 
	private void segurosLoad(MouseEvent event) {
		loadPage("SegurosPage");
	}
	
	@FXML 
	private void carritoLoad(MouseEvent event) {
		loadPage("CarritoPage");
	}
	
	@FXML
	private void close(MouseEvent event) {
		Platform.exit();
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

	//-----------------  CARRITO  --------------------
	//metodo que muestra y actualiza el carrito en la parte derecha de la pantalla cada vez que alguien pulsa. Al volver a pulsarlo se esconde.
	@FXML 
	private void muerstraCarrito(MouseEvent event) {
		if(c % 2 == 0) {
			apCarrito.setVisible(false);  //!!!OJO EL ESTADO DEL ANCHOR PANE DEL CARRITO ES INVISIBLE!!!!!!!!!
			c++;
		}
		else {
			bp.setRight(apCarrito);
			apCarrito.setVisible(true);
			setTextCarrito();
			c++;
		}
	}

	//metodo que muestra los productos que hay en el carrito en el campo de texto resrvado
	public void setTextCarrito() {
		//Cliente cliente=db.selectCliente(Db.getUsername()); 
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
		setTextPrecio();
	}
	//metodo que muestra el precio final de los productos que hay en el carrito
	public void setTextPrecio() {
		Cliente cliente=Db.getUserConnected();
		double pf=cliente.calculaPrecioCarrito();
		txtPrecioFinal.setText(""+pf);
	}
		
}

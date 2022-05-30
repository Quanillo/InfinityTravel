package application;

import java.io.IOException;
import java.util.ArrayList;
import Code.Cliente;
import Code.Producto;
import db.Db;
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

/**
 * Clase controladora de la ventana principal.
 */
public class MainInfinityClass {

	@FXML private Pane p;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	@FXML private AnchorPane apHome;
	@FXML private Button packs;
	@FXML private Button Billetes;
	@FXML private Button Alojamiento;
	@FXML private Button Vehiculos;
	@FXML private Button Experiencias;
	@FXML private Button Seguros;
	@FXML private Button exit;
	@FXML private Button User;
	@FXML private Button Home;
	//------Atributos del carrito------
	@FXML private AnchorPane apCarrito;
	@FXML private Button btnVerCarrito;
	@FXML private Text txtPrecioFinal;
	@FXML private Text txtCarrito;
	@FXML private TextFlow txtCarritoFlow;
	@FXML private Pane pNumProductos;
	@FXML private Text txtNumProductos;
	private long c=1; //contador para mostrar y esconder el carrito
	
	//------Metodos de carga de páginas-------
	
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
	private void userLoad(MouseEvent event) {
		loadPage("UserPage");
	}
	
	@FXML 
	private void homeLoad(MouseEvent event) {
		loadPage("HomePage");
	}
	
	@FXML
	private void close(MouseEvent event) {
		Platform.exit();
	}
	/**
	 * Método genérico para cargar en la interface cada una ded las páginas.
	 * @param page Nombre de la página que se quiere cargar.
	 */
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
	/**
	 * metodo que muestra y actualiza el carrito en la parte derecha de la pantalla cada vez que alguien pulsa. Al volver a pulsarlo se esconde.
	 * @param event
	 */
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
	/**
	 * Muestra los productos que hay en el carrito en el campo de texto resrvado
	 */
	public void setTextCarrito() {

		int numProdCarrito=0;
		Cliente cliente=Db.getUserConnected();
		ArrayList<Producto> listaProductos =cliente.getCarrito();
		String carrito="";
		if(listaProductos.isEmpty()) {
			carrito="No hay productos en el carrito";
		}
		else {
			for(int i=0; i<listaProductos.size(); i++) {
				carrito+=listaProductos.get(i).toString() + "\n";
				numProdCarrito++;
				System.out.println(listaProductos.get(i).toString());
			}
		}
		if(numProdCarrito>=1) {
			txtNumProductos.setText(""+numProdCarrito);
			pNumProductos.setVisible(true);
		}
		txtCarrito.setText(carrito);
		setTextPrecio();
		if (numProdCarrito<1)
			pNumProductos.setVisible(false);
	}
	//
	/**
	 * Muestra el precio final de los productos que hay en el carrito
	 */
	public void setTextPrecio() {
		Cliente cliente=Db.getUserConnected();
		double pf=cliente.calculaPrecioCarrito();
		txtPrecioFinal.setText(""+pf);
	}
	/**
	 * Deja los campos de la interface con los valores de inicio.
	 */
	public void limpiaCarrito() {
		txtPrecioFinal.setText(null);
		txtCarrito.setText(null);
		txtNumProductos.setText(null);
		pNumProductos.setVisible(false);
	}
	
}

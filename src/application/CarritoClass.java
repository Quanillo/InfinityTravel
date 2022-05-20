package application;



public class CarritoClass {


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
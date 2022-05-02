package Code;

import java.util.ArrayList;

public class User {
	
	public String nombre;
	public String pass;
	ArrayList<Reserva> listaReservas;
	ArrayList<Producto> listaProductos;
	
	public ArrayList<Producto> muestraProductos (){
		return listaProductos;
	}
	
}

//CODIGO PERRO
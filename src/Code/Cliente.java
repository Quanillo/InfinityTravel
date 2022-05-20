package Code;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

	private String username;
	private String pass;
	private String correo;
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fNacimiento;
	private int telefono;
	
	ArrayList<Producto> carrito = new ArrayList<Producto>();

	public Cliente(String username, String pass, String correo) {
		this.username = username;
		this.pass = pass;
		this.correo = correo;
	}

	public Cliente(String username, String pass, String correo, String nombre, String apellidos, String dni,
			LocalDate fNacimiento, int telefono) {
		this.username = username;
		this.pass = pass;
		this.correo = correo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fNacimiento = fNacimiento;
		this.telefono = telefono;
		this.carrito = new ArrayList<Producto>();
	}
	//metodo que calcula el precio final del array de productos carrito.
	public double calculaPrecioFinal () {
		double pf=0;
		for(int i=0; i<carrito.size(); i++) {
			pf+=carrito.get(i).getImporteProducto();
		}
		return Math.round(pf*100.0)/100.0;
	}
	
	public void addProducto(Producto p) {
		carrito.add(p);
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public LocalDate getfNacimiento() {
		return fNacimiento;
	}



	public void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}



	public int getTelefono() {
		return telefono;
	}



	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public ArrayList<Producto> getCarrito() {
		return carrito;
	}


	public void setCarrito(ArrayList<Producto> carrito) {
		this.carrito = carrito;
	}
	

	@Override
	public String toString() {
		return "Cliente [username=" + username + ", pass=" + pass + ", correo=" + correo + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", dni=" + dni + ", fNacimiento=" + fNacimiento + ", telefono="
				+ telefono + ", carrito=" + carrito + "]";
	}



	

	

	
	
}

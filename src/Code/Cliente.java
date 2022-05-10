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
	
	ArrayList<Reserva> listaReservas;
	
	

	public Cliente(String username, String pass, String correo) {
		super();
		this.username = username;
		this.pass = pass;
		this.correo = correo;
	}


	public Cliente(String username, String pass, String correo, String nombre, String apellidos, String dni,
			LocalDate fNacimiento, int telefono) {
		super();
		this.username = username;
		this.pass = pass;
		this.correo = correo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fNacimiento = fNacimiento;
		this.telefono = telefono;
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



	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}



	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}



	@Override
	public String toString() {
		return "Cliente [username=" + username + ", pass=" + pass + ", correo=" + correo + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", dni=" + dni + ", fNacimiento=" + fNacimiento + ", telefono="
				+ telefono + ", listaReservas=" + listaReservas + "]";
	}
	
	
	
}

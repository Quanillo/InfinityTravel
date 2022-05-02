package Code;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fNacimiento;
	private int telefono;
	private String correo;
	ArrayList<Reserva> listaReservas;
	
	public Cliente(String nombre, String apellidos, String dni, LocalDate fNacimiento, int telefono, String correo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fNacimiento = fNacimiento;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public Cliente(String nombre, String apellidos, String dni, String correo) {
		
	}
	
	public void reservaProducto(Pack p) {
		
	}
	
	public void reservaPack(Pack p) {
		
	}
	
	public void confirmarReserva(Reserva r) {
		
	}
	
	public void anularReserva(Reserva r) {
		
	}
	
	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	String getApellidos() {
		return apellidos;
	}

	void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	String getDni() {
		return dni;
	}

	void setDni(String dni) {
		this.dni = dni;
	}

	LocalDate getfNacimiento() {
		return fNacimiento;
	}

	void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	int getTelefono() {
		return telefono;
	}

	void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	String getCorreo() {
		return correo;
	}

	void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fNacimiento="
				+ fNacimiento + ", telefono=" + telefono + ", correo=" + correo + ", listaReservas=" + listaReservas
				+ "]";
	}
	
	
	
}

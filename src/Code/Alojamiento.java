package Code;

import java.time.LocalDate;

public class Alojamiento extends Producto{
	
	private String direccion;
	private String nombre;
	private String info;
	private String ciudad;
	private double precioNoche;
	
	public Alojamiento(String idItem, double importeProducto, LocalDate incio, LocalDate fin, String direccion, String nombre, String info, String ciudad, double precioNoche) {
		
		super(idItem, /*metodo que genera el importe segun el numero de noches*/importeProducto, incio, fin);
		
		this.direccion = direccion;
		this.nombre = nombre;
		this.info = info;
		this.ciudad = ciudad;
		this.precioNoche = precioNoche;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}
	
}
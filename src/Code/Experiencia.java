package Code;

import java.time.LocalDate;

public class Experiencia extends Producto{
	
	private String lugar;
	private String nombre;
	private String ciudad;
	
	public Experiencia(String idItem, double importeProducto, LocalDate incio, LocalDate fin, String lugar, String nombre, String ciudad) {
		
		super(idItem, importeProducto, incio, fin);
		
		this.lugar = lugar;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
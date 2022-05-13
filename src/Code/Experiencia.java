package Code;

public class Experiencia extends Producto{
	
	private String lugar;
	private String nombre;
	private String ciudad;
	
	public Experiencia(int idProducto, String nombre, String lugar, String ciudad) {
		super(idProducto);
		this.lugar = lugar;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	String getLugar() {
		return lugar;
	}

	void setLugar(String lugar) {
		this.lugar = lugar;
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	String getCiudadExp() {
		return ciudad;
	}

	void setCiudadExp(String ciudadExp) {
		this.ciudad = ciudadExp;
	}

	@Override
	public String toString() {
		return "Experiencia [lugar=" + lugar + ", nombre=" + nombre + ", ciudad=" + ciudad + "]";
	}
	
	

}

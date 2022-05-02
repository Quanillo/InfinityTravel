package Code;

public class Experiencia extends Producto{
	
	private String lugar;
	private String nombre;
	private Ciudad ciudadExp;
	
	public Experiencia(int idProducto, double importeProducto, String lugar, String nombre, Ciudad ciudadExp) {
		super(idProducto, importeProducto);
		this.lugar = lugar;
		this.nombre = nombre;
		this.ciudadExp = ciudadExp;
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

	Ciudad getCiudadExp() {
		return ciudadExp;
	}

	void setCiudadExp(Ciudad ciudadExp) {
		this.ciudadExp = ciudadExp;
	}

	@Override
	public String toString() {
		return "Experiencia [lugar=" + lugar + ", nombre=" + nombre + ", ciudadExp=" + ciudadExp + "]";
	}
	
	

}

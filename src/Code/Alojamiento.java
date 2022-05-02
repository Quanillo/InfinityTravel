package Code;

public class Alojamiento extends Producto{
	
	private String direccion;
	private tipoAlojamiento tipo;
	private String nombre;
	private String info;
	Ciudad ciudadAlojamiento;
	
	public Alojamiento(int idProducto, double importeProducto, String direccion, tipoAlojamiento tipo, String nombre,
			String info, Ciudad ciudadAlojamiento) {
		super(idProducto, importeProducto);
		this.direccion = direccion;
		this.tipo = tipo;
		this.nombre = nombre;
		this.info = info;
		this.ciudadAlojamiento = ciudadAlojamiento;
	}

	String getDireccion() {
		return direccion;
	}

	void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	tipoAlojamiento getTipo() {
		return tipo;
	}

	void setTipo(tipoAlojamiento tipo) {
		this.tipo = tipo;
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	String getInfo() {
		return info;
	}

	void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Alojamiento [direccion=" + direccion + ", tipo=" + tipo + ", nombre=" + nombre + ", info=" + info
				+ ", ciudadAlojamiento=" + ciudadAlojamiento + "]";
	}
	
	

}

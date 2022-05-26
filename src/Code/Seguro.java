package Code;

public class Seguro extends Producto {
	
	private String nombre;

	public Seguro(String idProducto, double importeProducto, String nombre) {
		
		super(idProducto, importeProducto, null, null);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
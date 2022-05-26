package Code;

public class Alojamiento extends Producto {

	private String nombre;
	private String direccion;
	private String id_ciudad;
	private String url;
	
	public Alojamiento(String idProducto, double importeProducto, String nombre, String direccion, String id_ciudad, String url) {
		
		super(idProducto, importeProducto, null, null);
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.id_ciudad = id_ciudad;
		this.url = url;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getId_ciudad() {
		return id_ciudad;
	}
	
	public void setId_ciudad(String id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
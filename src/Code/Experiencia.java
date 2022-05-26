package Code;

public class Experiencia extends Producto {
	
	private String nombre;
	private String ubicacion;
	private String id_ciudad;
	private String url;
	
	public Experiencia(String idProducto, double importeProducto, String nombre, String ubicacion, String id_ciudad, String url) {
		
		super(idProducto, importeProducto, null, null);
		
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.id_ciudad = id_ciudad;
		this.url = url;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
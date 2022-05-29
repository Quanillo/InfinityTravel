package Code;

/**
 * Esta clase construye productos de tipo seguro.
 */
public class Seguro extends Producto {
	
	private String nombre;

	public Seguro(String idProducto, double importeProducto, String nombre) {
		
		super(idProducto, importeProducto, null, null);
		this.nombre = nombre;
	}

	public static String descripcionEco() {
		return "Seguro básico para que la mama se quede tranquila en tu interrail, pero que realmente se lavará las manos"
				+ " cuando vuelques a las 3am en un garito de Budapest.";
	}
	
	public static String descripcionLux() {
		return "Seguro más robusto, que cuidará de ti y de los tuyos cuando tu hijo prepuber la líe en el Acuarium"
				+ " de París liberando a Willy";
	}
	
	public static String descripcionPro() {
		return "Seguro a prueba de bombas. Cubre todos los imprevistos que puedan pasar, desde televisores tirados por la ventana del hotel"
				+ " hasta repatriación del cuerpo en caso de fallecimiento. Perfecto para giras internacionales de grupos musicales en la cresta de la ola";
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getClass().getSimpleName())+"\n"+ nombre +"\n"+ super.getIncio()+ " | " +super.getFin() + "\n"+super.getImporteProducto()+" €\n";
	}
	
}
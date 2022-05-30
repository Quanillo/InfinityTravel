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
		return "Nos aseguraremos de que tu equipaje llegue tal y como lo viste por ultima vez.";
	}
	
	public static String descripcionLux() {
		return "Seremos tu angel de la guarda durante todo el viaje. Tendrás a un equipo 24h a tu disposicion al que puedes contactar para cualquier imprevisto así como de un dispositivo movil de sustitución. "
				+ "Ideal si buscas más una aventura que un viaje. Incluye lo mencionado en los seguros Economic y Pro.";
	}
	
	public static String descripcionPro() {
		return "Disfruta de asistencia médica en centros asociados en tu ciudad de destino. Incluye el seguro Economic.";
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
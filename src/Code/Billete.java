package Code;

import java.sql.Date;
import java.time.LocalDate;

import BBDD.Db;

public class Billete extends Producto{

	private String origen;
	private String destino;
	
	final static double precioCombustible = 1;

	static Db db;
	
	

	public Billete(String origen, String destino, LocalDate incio) {

		super(billete_generateId(origen, destino), billete_getPrecio(origen, destino), incio);

		this.origen = origen;
		this.destino = destino;
	}

	public static double billete_getPrecio(String origen, String destino) {
		
		double lat1 = db.ciudad_getLatitud(origen);
		double lon1 = db.ciudad_getLongitud(origen);
		double lat2 = db.ciudad_getLatitud(origen);;
		double lon2 = db.ciudad_getLongitud(origen);;
		
		//FORMULA DE HAVERSINE
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		double a = Math.pow(Math.sin(dLat/2),2) + Math.pow(Math.sin(dLon/2),2) * Math.cos(lat1) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c * precioCombustible; // km
	}
	
	public static String billete_generateId(String origen, String destino) {
		
		return origen.substring(0, 4).toUpperCase() + destino.substring(0, 4).toUpperCase() + db.producto_generateId();
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}
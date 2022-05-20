package Code;

import java.time.LocalDate;

import BBDD.Db;
import BBDD.DbCiudad;
import BBDD.DbProducto;

public class Billete extends Producto{

	private String origen;
	private String destino;
	
	final static double precioCombustible = 1;

	static Db db = new Db();
	static DbCiudad dbc = new DbCiudad();
	
	public Billete() {
		
	}

	public Billete(String origen, String destino, LocalDate fecha) {

		super(billete_generateId(origen, destino), billete_getPrecio(origen, destino), fecha);

		this.origen = origen;
		this.destino = destino;
	}

	public static double billete_getPrecio(String origen, String destino) {
		
		double lat1 = dbc.ciudad_getLatitud(origen);
		double lon1 = dbc.ciudad_getLongitud(origen);
		double lat2 = dbc.ciudad_getLatitud(destino);;
		double lon2 = dbc.ciudad_getLongitud(destino);;
		
		
		//FORMULA DE HAVERSINE
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		double a = Math.pow(Math.sin(dLat/2),2) + Math.pow(Math.sin(dLon/2),2) * Math.cos(lat1) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return Math.round((rad * c * precioCombustible)*100.0)/100.0;
	}
	
	public static String billete_generateId(String origen, String destino) {
		
		String s = origen.substring(0, 3).toUpperCase() + destino.substring(0, 3).toUpperCase() + DbProducto.producto_generateId();
		return s;
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
	
	public String getIdBillete () {
		return super.getIdProducto();
	}
	
	public double getImporteBillete () {
		return super.getImporteProducto();
	}
	
	public LocalDate getFechaBillete () {
		return super.getIncio();
	}

	@Override
	public String toString() {
		return String.valueOf(this.getClass().getSimpleName())+"\n"+origen+" > "+destino+" | "+super.getIncio()+"\n"+super.getImporteProducto()+" €";
	}

}
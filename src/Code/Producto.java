package Code;

import java.sql.Date;

public abstract class Producto {
	
	private String idProducto;
	private double importeProducto;
	private Date incio;
	private Date fin;
	
	public Producto(String idProducto, double importeProducto, Date incio, Date fin) {
		
		this.idProducto = idProducto;
		this.importeProducto = importeProducto;
		this.incio = incio;
		this.fin = fin;
	}

	public Producto(String idProducto, double importeProducto, Date incio) {
		
		this.idProducto = idProducto;
		this.importeProducto = importeProducto;
		this.incio = incio;
	}
	
	public Producto() {}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public double getImporteProducto() {
		return importeProducto;
	}

	public void setImporteProducto(double importeProducto) {
		this.importeProducto = importeProducto;
	}

	public Date getIncio() {
		return incio;
	}

	public void setIncio(Date incio) {
		this.incio = incio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	} 
}
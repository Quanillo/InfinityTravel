package code;

import java.time.LocalDate;

/**
 * Clase abstracta padre de todas las clases de productos especificos.
 */
public abstract class Producto {
	
	private String idProducto;
	private double importeProducto;
	private LocalDate incio;
	private LocalDate fin;
	
	public Producto (String idProducto, double importeProducto, LocalDate incio, LocalDate fin) {
		
		this.idProducto = idProducto;
		this.importeProducto = importeProducto;
		this.incio = incio;
		this.fin = fin;
	}

	public Producto (String idProducto, double importeProducto, LocalDate incio) {
		
		this.idProducto = idProducto;
		this.importeProducto = importeProducto;
		this.incio = incio;
	}
	
	public Producto(double importeProducto, LocalDate incio) {
		
		
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

	public LocalDate getIncio() {
		return incio;
	}

	public void setIncio(LocalDate incio) {
		this.incio = incio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	} 
}
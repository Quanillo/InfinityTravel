package Code;

import java.time.LocalDate;

public abstract class Producto {
	
	private String idItem;
	private double importeProducto;
	private LocalDate incio;
	private LocalDate fin;
	
	public Producto(String idItem, double importeProducto, LocalDate incio, LocalDate fin) {
		
		this.idItem = idItem;
		this.importeProducto = importeProducto;
		this.incio = incio;
		this.fin = fin;
	}

	public Producto(String idItem, double importeProducto, LocalDate incio) {
		
		this.idItem = idItem;
		this.importeProducto = importeProducto;
		this.incio = incio;
	}
	
	public Producto() {}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idProducto) {
		this.idItem = idProducto;
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
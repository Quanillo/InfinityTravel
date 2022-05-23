package Code;

import java.time.LocalDate;

public class Seguro extends Producto{
	
	private String categoria;
	private double precioSemana;
	
	public Seguro(String idItem, double importeProducto, LocalDate incio, LocalDate fin, String categoria, double precioSemana) {
		
		super(idItem, importeProducto, incio, fin);
		
		this.categoria = categoria;
		this.precioSemana = precioSemana;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecioSemana() {
		return precioSemana;
	}

	public void setPrecioSemana(double precioSemana) {
		this.precioSemana = precioSemana;
	}

}
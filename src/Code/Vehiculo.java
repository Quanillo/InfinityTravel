package Code;

import java.time.LocalDate;

public class Vehiculo extends Producto{
	
	private String tipoVehiculo;
	private double precioDia;
	
	public Vehiculo(String idItem, double importeProducto, LocalDate incio, LocalDate fin, String tipoVehiculo, double precioDia) {
		
		super(idItem, importeProducto, incio, fin);
		
		this.tipoVehiculo = tipoVehiculo;
		this.precioDia = precioDia;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public double getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}
	
}
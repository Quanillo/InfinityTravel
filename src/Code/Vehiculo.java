package Code;

import java.util.Date;

public class Vehiculo extends Producto{
	
	private String tipoVehiculo;
	private String matricula;
	private String ciudadDeRecogida;
	private String ciudadDeVuelta;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	public Vehiculo(int idProducto, double importeProducto, String tipoVehiculo, String matricula) {
		super(idProducto);
		this.tipoVehiculo = tipoVehiculo;
		this.matricula = matricula;
	}

	String getTipoVehiculo() {
		return tipoVehiculo;
	}
	void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	String getMatricula() {
		return matricula;
	}
	void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Vehiculo [tipoVehiculo=" + tipoVehiculo + ", matricula=" + matricula + "]";
	}
	
	

}

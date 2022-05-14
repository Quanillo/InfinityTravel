package Code;

public class Vehiculo extends Producto{
	
	private String tipoVehiculo;
	private String matricula;
	
	
	public Vehiculo() { super(); }

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
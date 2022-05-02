package Code;

public class Billete extends Producto{
	
	private int codigo;
	private String medio;
	private String asiento;
	private String terminalSalida;
	private String terminalDestino;
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	
	public Billete(int idProducto, double importeProducto, int codigo, String medio, String asiento,
			String terminalSalida, String terminalDestino, Ciudad ciudadOrigen, Ciudad ciudadDestino) {
		super(idProducto, importeProducto);
		this.codigo = codigo;//autogenerado
		this.medio = medio;
		this.asiento = asiento;//autogenerado
		this.terminalSalida = terminalSalida;
		this.terminalDestino = terminalDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
	}

	int getCodigo() {
		return codigo;
	}

	void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	String getMedio() {
		return medio;
	}

	void setMedio(String medio) {
		this.medio = medio;
	}

	String getAsiento() {
		return asiento;
	}

	void setAsiento(String asiento) {
		this.asiento = asiento;
	}

	String getTerminalSalida() {
		return terminalSalida;
	}

	void setTerminalSalida(String terminalSalida) {
		this.terminalSalida = terminalSalida;
	}

	String getTerminalDestino() {
		return terminalDestino;
	}

	void setTerminalDestino(String terminalDestino) {
		this.terminalDestino = terminalDestino;
	}

	Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}

	void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	Ciudad getCiudadDestino() {
		return ciudadDestino;
	}

	void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	@Override
	public String toString() {
		return "Billete [codigo=" + codigo + ", medio=" + medio + ", asiento=" + asiento + ", terminalSalida="
				+ terminalSalida + ", terminalDestino=" + terminalDestino + ", ciudadOrigen=" + ciudadOrigen
				+ ", ciudadDestino=" + ciudadDestino + "]";
	}
	
	

}

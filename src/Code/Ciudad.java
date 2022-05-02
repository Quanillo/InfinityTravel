package Code;

public class Ciudad {

	private int idCiudad;
	private String nombreCiudad;
	private String pais;
	private Destino destino;
	
	public Ciudad(int idCiudad, String nombreCiudad, String pais) {
		super();
		this.idCiudad = idCiudad;
		this.nombreCiudad = nombreCiudad;
		this.pais = pais;
	}

	int getIdCiudad() {
		return idCiudad;
	}

	void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	String getNombreCiudad() {
		return nombreCiudad;
	}

	void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	String getPais() {
		return pais;
	}

	void setPais(String pais) {
		this.pais = pais;
	}

	Destino getDestino() {
		return destino;
	}

	void setDestino(Destino destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", nombreCiudad=" + nombreCiudad + ", pais=" + pais + ", destino="
				+ destino + "]";
	}
	
	
}

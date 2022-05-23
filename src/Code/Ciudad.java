package Code;

public class Ciudad {

	private int idCiudad;
	private String nombreCiudad;
	
	public Ciudad(int idCiudad, String nombreCiudad) {
		this.idCiudad = idCiudad;
		this.nombreCiudad = nombreCiudad;
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
}
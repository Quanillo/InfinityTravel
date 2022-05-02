package Code;

import java.util.ArrayList;

public class Destino {

	private int idDestino;
	private String nombre;
	ArrayList<Ciudad>listaCiudades;
	
	public Destino(int idDestino, String nombre) {
		super();
		this.idDestino = idDestino;
		this.nombre = nombre;
	}

	int getIdDestino() {
		return idDestino;
	}

	void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	ArrayList<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	void setListaCiudades(ArrayList<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	@Override
	public String toString() {
		return "Destino [idDestino=" + idDestino + ", nombre=" + nombre + ", listaCiudades=" + listaCiudades + "]";
	}
	
	
	
}

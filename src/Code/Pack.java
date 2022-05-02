package Code;

import java.util.ArrayList;

public class Pack {
	
	private int idPack;
	private double importePack;
	ArrayList<Producto> listaProductos;
	
	public Pack(int idPack, double importePack) {
		super();
		this.idPack = idPack;
		this.importePack = importePack;
	}

	public void aniadeProducto(Producto p) {
		
	}
	
	public void eliminaProducto(Producto p) {
		
	}
	
	int getIdPack() {
		return idPack;
	}

	void setIdPack(int idPack) {
		this.idPack = idPack;
	}

	double getImportePack() {
		return importePack;
	}

	void setImportePack(double importePack) {
		this.importePack = importePack;
	}

	@Override
	public String toString() {
		return "Pack [idPack=" + idPack + ", importePack=" + importePack + ", listaProductos=" + listaProductos + "]";
	}
	
	
	
}

package Code;

public abstract class Producto {
	
	private int idProducto;
	private double importeProducto;
	private String incio;
	private String fin;
	
	public Producto(int idProducto, double importeProducto, String incio, String fin) {
		this.idProducto = idProducto;
		this.importeProducto = importeProducto;
	}
	
	public Producto(int idProducto) {
		// TODO Auto-generated constructor stub
	}

	public void anularProducto(Producto p) {
		
	}
	
	int getIdProducto() {
		return idProducto;
	}

	void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	double getImporteProducto() {
		return importeProducto;
	}

	void setImporteProducto(double importeProducto) {
		this.importeProducto = importeProducto;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", importeProducto=" + importeProducto + "]";
	}
	
	

}

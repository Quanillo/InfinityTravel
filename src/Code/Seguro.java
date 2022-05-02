package Code;

public class Seguro extends Producto{
	
	private String categoria;

	public Seguro(int idProducto, double importeProducto, String categoria) {
		super(idProducto, importeProducto);
		this.categoria=categoria;
	}

	String getCategoria() {
		return categoria;
	}

	void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Seguro [categoria=" + categoria + "]";
	}

	
	
}

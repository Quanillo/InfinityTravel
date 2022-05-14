package Code;

public class Seguro extends Producto{
	
	private String categoria;

	public Seguro() { super(); }

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

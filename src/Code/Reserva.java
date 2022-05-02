package Code;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva {

	private Cliente cliente;
	private LocalDate fechaReserva;
	private int idReserva;
	private double importeTotal;
	ArrayList<Producto> listaProductos;
	ArrayList<Pack> listapack;
	
	public Reserva(Cliente cliente, LocalDate fechaReserva, int idReserva, double importeTotal) {
		super();
		this.fechaReserva = fechaReserva;
		this.idReserva = idReserva;
		this.importeTotal = importeTotal;
		this.cliente=cliente;
	}
	
	public void anadirProducto(Producto p) {
		listaProductos.add(p);
	}
	
	public void anadirPack(Pack p) {
		listapack.add(p);
	}

	Cliente getCliente() {
		return cliente;
	}

	void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	LocalDate getFechaReserva() {
		return fechaReserva;
	}

	void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	int getIdReserva() {
		return idReserva;
	}

	void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	double getImporteTotal() {
		return importeTotal;
	}

	void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	@Override
	public String toString() {
		return "Reserva [cliente=" + cliente + ", fechaReserva=" + fechaReserva + ", idReserva=" + idReserva
				+ ", importeTotal=" + importeTotal + "]";
	}
	
	
	
}

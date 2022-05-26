package BBDD;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Code.Alojamiento;

public class ListaAlojamientos {

	@Test
	public void test() {
		DbProducto dbp = new DbProducto();
		ArrayList<Alojamiento> listaAlojamientos=dbp.getAlojamientos("MADRID");
		String h1=listaAlojamientos.get(0).getNombre();
		assertEquals(true, "Hotel Gran via");
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Code.Billete;
import db.Db;
import db.DbCiudad;

public class EjemploDePrecioDeBillete {

	@Test
	public void test() {
		Db db = new Db();
		DbCiudad dbc = new DbCiudad();
		db.connect();
		dbc.connect();
		boolean x = false;
		double n = Billete.billete_getPrecio("Madrid", "Roma");
		if (n>0)
			x = true;
		System.out.println(n);
		assertEquals(true, x);
	}
}
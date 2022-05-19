package application;

import static org.junit.Assert.*;

import org.junit.Test;

import BBDD.Db;
import BBDD.DbCiudad;
import Code.Billete;

public class BilletesClassTest {

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
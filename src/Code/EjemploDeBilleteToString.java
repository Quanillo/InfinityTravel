package Code;

import java.time.LocalDate;

import org.junit.Test;

import BBDD.Db;
import BBDD.DbCiudad;
import BBDD.DbProducto;

public class EjemploDeBilleteToString {

	@Test
	public void test() {
		Db db = new Db();
		DbCiudad dbc = new DbCiudad();
		DbProducto dbp = new DbProducto();
		db.connect();
		dbc.connect();
		dbp.connect();
		Billete b = new Billete("Madrid","Roma", LocalDate.MAX );
		System.out.println(b);
	}
}
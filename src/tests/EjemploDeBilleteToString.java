package tests;

import java.time.LocalDate;

import org.junit.Test;

import code.Billete;
import db.Db;
import db.DbCiudad;
import db.DbProducto;

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
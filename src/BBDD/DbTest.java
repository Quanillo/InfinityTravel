package BBDD;

import static org.junit.Assert.*;

import org.junit.Test;

public class DbTest {

	@Test
	public void test_username_already_exists() {
		Db db = new Db();
		db.connect();
		boolean res = db.register_usernameAlreadyExists("admin");
		
		assertEquals(true, res);
	}

}
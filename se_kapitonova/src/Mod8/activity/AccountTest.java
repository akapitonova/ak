package Mod8.activity;

import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	public void testNormalAccountCreation() throws AccountException, Exception{
		String name = "account868";
		Account account = new Account(name);
		assertEquals(name, account.getName());
	}
	
	public void testInvalidAccountCreation() throws Exception{
		testThrowAccountException();
	}
	
	public void testThrowAccountException() throws AccountException{
		String name = "account";
		try {
			new Account(name);
		} catch (Exception e) {
			assertTrue(e instanceof Exception);
		}
	}
	
	public void testAccountExceptionMessageSent() throws AccountException{
		String name = "account";
		try {
			new Account(name);
		} catch (Exception e) {
			assertEquals(AccountException.NAME_TOO_SIMPLE +" : "+ name, e.getMessage());
		}
	}
}

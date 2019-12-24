package Mod7.activity;

import junit.framework.TestCase;

public class DenominationTest extends TestCase {

	public void testCreateDenomination(){
		Denomination denomination = new DenominationImpl("N", "D", "S");
		assertEquals("N", denomination.getName());
		assertEquals("D", denomination.getDescription());
		assertEquals("S", denomination.getSymbol());
	}

}

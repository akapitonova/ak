package Mod7.activity;

import junit.framework.TestCase;

public class CurrencyTest extends TestCase {

	/**
	 *   
	 * 
	 * 
	 * 
	 * 
	 */

		
	public void testCreateCurrency(){
		Denomination denomination = new DenominationImpl("N", "D", "S");
		Currency currency = new CurrencyImpl(18, denomination);
		assertEquals((float) 18, currency.getValue());
		assertEquals("N", currency.getDenomination().getName());
		assertEquals("D", currency.getDenomination().getDescription());
		assertEquals("S", currency.getDenomination().getSymbol());
	}
}

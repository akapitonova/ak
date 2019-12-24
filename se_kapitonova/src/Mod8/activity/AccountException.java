package Mod8.activity;

/**
 * This class represents the exception that can be thrown if the
 * name given to an Account instance violates naming rules
 * 
 * @author John Doe
 *
 */
@SuppressWarnings("serial")
public class AccountException extends Exception{

	
	public static final String NAME_TOO_SHORT = "Name must be longer than 4 characters";
	public static final String NAME_TOO_SIMPLE = "Name must contain a combination of letters and numbers"; 
	
	private String name;
	
	/**
	 * Constructs an AcountException
	 * 
	 * @param message The message to be set explaining the name violation (see static attributes)
	 * @param name The actual name 
	 */
	public AccountException(String message, String name) throws Exception {
		this.name = name;
		throw new Exception(message + " : " + name);
	}
	
	/**
	 * Returns the name passed to this Account exception
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	
	
}

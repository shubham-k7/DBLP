//! Custom Exception
/*!
Used for throwing and catching custom excpetions
*/
public class CustomEx extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String str1;
	//!  Default Constructor
	/*!
	intitialising Custom Exception
	*/
	public CustomEx(String str2) {
		str1 = str2;
		// TODO Auto-generated constructor stub
	}
	//!custom toString method 
		/*!
		default tostring method overrided
		*/
	public String toString() {
		return ("Exception Thrown = " + str1 + "\nRecovering ....");
	}

}
	
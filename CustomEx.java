//!  Custom Exception Class
/*!
Used for throwing anad catching custom exception
*/
public class CustomEx extends Exception {
	String str1;

	public CustomEx(String str2) {
		str1 = str2;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return ("Exception Thrown = " + str1 + "\nRecovering ....");
	}

}

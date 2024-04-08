/**
 * 
 */
package tech.ada.sb.exception;

/**
 * 
 */
public class SaldoIndisponivelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 976865063585788424L;

	public SaldoIndisponivelException(String message) {
		super(message);
	}

	public SaldoIndisponivelException(String message, Throwable cause) {
		super(message, cause);
	}

}

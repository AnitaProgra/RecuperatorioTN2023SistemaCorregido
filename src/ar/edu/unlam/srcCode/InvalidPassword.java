package ar.edu.unlam.srcCode;

public class InvalidPassword extends Exception {

	protected String mensaje;

	public InvalidPassword(String mensaje) {
		super(mensaje);
		
	} 
	
	
}

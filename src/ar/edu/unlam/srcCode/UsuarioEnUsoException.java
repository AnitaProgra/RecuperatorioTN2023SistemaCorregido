package ar.edu.unlam.srcCode;

public class UsuarioEnUsoException extends Exception {

	
	protected String mensaje;

	public UsuarioEnUsoException(String mensaje) {
		super(mensaje);
	} 
	
	
}

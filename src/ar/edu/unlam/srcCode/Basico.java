package ar.edu.unlam.srcCode;

public class Basico extends Usuario {

	
	 protected Integer numeroDeLogueos; 
	 
	public Basico(String usuario, String contrasenia) {
		super(usuario, contrasenia);
		
	}

	@Override
	public String getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(String usuario) {
		this.usuario= usuario; 
	}

	@Override
	public String getContrasenia() {
		return contrasenia;
	}

	@Override
	public void setContrasenia(String contrasenia) {
		this.contrasenia= contrasenia;  
		
	}

	
	
	

	
}

package ar.edu.unlam.srcCode;

public class Admin extends Usuario {

	public Admin(String usuario, String contrasenia) {
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

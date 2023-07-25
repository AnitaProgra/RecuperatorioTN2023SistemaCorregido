package ar.edu.unlam.srcCode;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Usuario implements Comparable<Usuario> {

	protected String usuario; 
	protected String contrasenia;
	protected int logueosNoExitosos; 
	
	
	public Usuario(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}



    public void incrementarLogueosNoExitosos() {
        logueosNoExitosos ++;
    }
	
	
	public int getLogueosNoExitosos() {
		return logueosNoExitosos;
	}


	public void setLogueosNoExitosos(int logueosNoExitosos) {
		this.logueosNoExitosos = logueosNoExitosos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(usuario, other.usuario);
	}
	
	
	@Override
	public int compareTo(Usuario o) {
		return this.usuario.compareTo(o.getUsuario());
	}
	
	
	
}

package ar.edu.unlam.srcCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class BaseDeDatos {

	
	protected String nombre; 
	protected HashSet<Usuario> usuarios; 
	protected HashSet<Usuario> usuariosBloqueados; 
	protected HashSet<Usuario> usuariosEliminados;
	protected HashSet<Usuario> usuariosLogueados;
	
	public BaseDeDatos(String nombre) {
		this.nombre = nombre;
		this.usuariosBloqueados = new HashSet<>();
		this.usuariosEliminados = new HashSet<>();
		this.usuarios = new HashSet<>();
		this.usuariosLogueados = new HashSet<>();
	}


	public String getNombre() {
		return nombre;
	}

	
	

	public HashSet<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public boolean agregarUsuario(Usuario usuario) throws UsuarioEnUsoException {
		try {
			if(!usuarios.contains(usuario)) {
				usuarios.add(usuario); 
			
			}
			
			else {throw new UsuarioEnUsoException("este usuario ya esta en uso, escoja otro por favor");}
			return true;
		}
		

		catch(UsuarioEnUsoException e) {
			System.out.println("Disculpe, " + usuario.getUsuario()+" " + e.getMessage());
			return false; 
		}
		
	}
	
	
	public Integer numeroUsuarios() {
		return usuarios.size();  
	}
	
	
	public Integer numeroUsuariosBloqueados() {
		return usuariosBloqueados.size();  
	}
	
	
	public Integer numeroUsuariosEliminados() {
		return usuariosEliminados.size();  
	}
	
	public boolean bloquear(Usuario usuario) {
			if(usuario instanceof Basico && usuarios.contains(usuario)) {
				usuariosBloqueados.add(usuario); 
				return true;
			}
			return false; 
	}
	
	
	
	public boolean eliminar(Usuario usuario) throws ClassCastException{
	  try {
		  if(usuario instanceof Basico && usuarios.contains(usuario)) {
			  usuarios.remove(usuario); 
			  usuariosEliminados.add(usuario);
  
		  }
		  if (usuario instanceof Admin)
		  {throw new ClassCastException ("este usuario no es eliminable porque es administrador"); }
		  
		  return true; 		    
	  }
	  catch(ClassCastException e) {
		  System.out.println(e.getMessage()); 
		  return false; 
	  }
	 	
	}
	
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public HashSet<Usuario> getUsuariosBloqueados() {
		return usuariosBloqueados;
	}


	public void setUsuariosBloqueados(HashSet<Usuario> usuariosBloqueados) {
		this.usuariosBloqueados = usuariosBloqueados;
	}


	public HashSet<Usuario> getUsuariosEliminados() {
		return usuariosEliminados;
	}


	public void setUsuariosEliminados(HashSet<Usuario> usuariosEliminados) {
		this.usuariosEliminados = usuariosEliminados;
	} 


	
	
	
	
	
	public HashSet<Usuario> getUsuariosLogueados() {
		return usuariosLogueados;
	}


	public void setUsuariosLogueados(HashSet<Usuario> usuariosLogueados) {
		this.usuariosLogueados = usuariosLogueados;
	}


	
	
	public boolean login(Usuario usuario, String contrasenia) throws UserNotFound{
	  try { 
		if (usuarios.contains(usuario) && usuario.getContrasenia().equals(contrasenia)) {
			this.usuariosLogueados.add(usuario);
	        return true;
	    }


	    
		if (!usuario.getContrasenia().equals(contrasenia))
	    {usuario.incrementarLogueosNoExitosos();
	    System.out.println("usuario o contrasenia invalida");
    	
	    if(usuario.getLogueosNoExitosos()==3) {
	    	bloquear(usuario);
	    	
	    }
	    
	    }
	    
	    
	    if(!usuarios.contains(usuario))
	    {throw new UserNotFound("UserNotFound");}
		 
	    		
		}
	  
	  
	  catch(UserNotFound e) {
		  System.out.println(e.getMessage()); 
		  return false; 
	  }
		return false;
		 
	}
	
	
	
	
	public TreeSet<Usuario> ordenarPorNombreDeUsuario() {
		
		if(this.usuarios.isEmpty()) {
			return null; 
		}
		
		TreeSet<Usuario> usuariosOrdenados = new TreeSet<>(usuarios); 
		
		for(Usuario u: usuariosOrdenados) {
			System.out.println(u.getUsuario());
		}
		return usuariosOrdenados;
	}
	
	
	public boolean validarContrasenia(Usuario usuario) throws InvalidPassword {
		try {
			if(usuarios.contains(usuario) && usuario.getContrasenia().matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!¡?¿])(?=\\S+$).{8,}$")) {
		
			return true; 
			}
			else {throw new InvalidPassword("contrasenia invalida, debe tener al menos una letra, un nro y un caracter especial");}
		}
			
		
		catch (InvalidPassword e) {
				System.out.println(e.getMessage());
				return false; }
			
	}
	
	
	
}

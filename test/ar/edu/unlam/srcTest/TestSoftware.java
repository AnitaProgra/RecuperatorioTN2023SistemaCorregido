package ar.edu.unlam.srcTest;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.srcCode.Admin;
import ar.edu.unlam.srcCode.BaseDeDatos;
import ar.edu.unlam.srcCode.Basico;
import ar.edu.unlam.srcCode.ClassCastException;
import ar.edu.unlam.srcCode.InvalidPassword;
import ar.edu.unlam.srcCode.UserNotFound;
import ar.edu.unlam.srcCode.Usuario;
import ar.edu.unlam.srcCode.UsuarioEnUsoException;

public class TestSoftware {

	@Test
	public void testCrearUnUsuarioBasico() throws UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
		
		Usuario basico1 = new Basico("Maia Britez", "1aam56jku"); 
		
		software.agregarUsuario(basico1);
		
		assertEquals((Integer)1, software.numeroUsuarios()); 
		assertTrue(software.getUsuarios().contains(basico1)); 
		
	}

	
	@Test
	public void testCrearUnUsuarioAdmin() throws UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
		
		Usuario admin1 = new Admin("Maia Britez", "1aam56jku"); 
		
		software.agregarUsuario(admin1);
		
		assertEquals((Integer)1, software.numeroUsuarios()); 
		assertTrue(software.getUsuarios().contains(admin1)); 
		
	}

	
	@Test
	public void testQueNoPuedaHaberDosOMasUsuariosConElMismoNombre() throws UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
		
		Usuario basico1 = new Basico("Maia Britez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario admin1 = new Basico("Martin Gomez", "880m56jku"); 
		Usuario basico4 = new Basico("Martin Gomez", "1aam095"); 
		
		software.agregarUsuario(basico1);
		software.agregarUsuario(basico2);
		software.agregarUsuario(admin1);
		
		
		assertFalse(software.agregarUsuario(basico4)); 
		
	}
	
	@Test
	public void testQueSePuedaBloquearUnUsuarioBasico() throws UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
		
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		
		
		software.agregarUsuario(basico1);
		software.agregarUsuario(basico2);
		software.agregarUsuario(basico3);
		
		software.bloquear(((Basico)basico3));
		
		//assertTrue(software.bloquear(((Basico)basico3)));
		//assertEquals((Integer)1, software.numeroUsuariosBloqueados());
		assertTrue(software.getUsuariosBloqueados().contains(basico3));
	}
	
	
	@Test
	public void testQueSePuedaEliminarUnUsuarioBasico() throws ClassCastException, UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
		
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		
		software.agregarUsuario(basico1);
		software.agregarUsuario(basico2);
		software.agregarUsuario(basico3);
		
		software.eliminar(basico1); 
		software.eliminar(basico2); 
		software.eliminar(basico3); 
		
		assertEquals((Integer)3, software.numeroUsuariosEliminados()); 
	
	}
	
	

	@Test
	public void testQueNoSePuedaEliminarUnUsuarioAdmin() throws ClassCastException, UsuarioEnUsoException {
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		Usuario admin1 = new Admin("Martin Gomez", "880m56jku"); 

		software.agregarUsuario(basico3);
		software.agregarUsuario(admin1);
		
		assertFalse(software.eliminar(admin1)); 
	
	}
	
	
	@Test
	public void testQueUnaVezEliminadosLosUsuariosSeBorrenDeLaBaseDeDatos() throws ClassCastException, UsuarioEnUsoException{
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		Usuario admin1 = new Basico("Martin Gomez", "880m56jku"); 

		//agrego 4 usuarios
		
		software.agregarUsuario(basico3);
		software.agregarUsuario(admin1);
		software.agregarUsuario(basico2);
		software.agregarUsuario(basico1);
		
		//elimino un usuario
		software.eliminar(((Basico)basico3));  
		
		//espero que de los 4 se elimine de la lista el que elimine
		assertEquals((Integer)3, software.numeroUsuarios()); 
	
	}
	
	
	@Test
	public void testQueUnUsuarioQueSeLogueeMasDe3VecesSeBloquee() throws UserNotFound, UsuarioEnUsoException{
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		Usuario admin1 = new Basico("Martin Gomez", "880m56jku"); 

		//agrego 4 usuarios
		
		software.agregarUsuario(basico3);
		software.agregarUsuario(admin1);
		software.agregarUsuario(basico2);
		
		software.login(basico3, "13am56ju");
		software.login(basico3, "13am56ju");

		software.login(basico3, "13am56ju");

		assertTrue(software.getUsuariosBloqueados().contains(basico3)); 
		
	}
	
	
	@Test
	public void testQueUnUsuarioQueNoExisteNoSePuedaLoguear() throws UserNotFound, UsuarioEnUsoException{
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		Usuario admin1 = new Basico("Martin Gomez", "880m56jku"); 


		software.agregarUsuario(basico3);
		software.agregarUsuario(admin1);
		software.agregarUsuario(basico2);
		
		

		//assertFalse(software.getUsuariosLogueados().contains(basico1)); 
		assertFalse(software.login(basico1, "1aam56jku")); 
	}
	
	
	@Test
	public void testQueSeDevuelvanLosUsuariosOrdenadosPorNombre() throws UsuarioEnUsoException{
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico1 = new Basico("Carla Perez", "1aam56jku"); 
		Usuario basico2 = new Basico("Jose Perez", "13am56jku"); 
		Usuario basico3 = new Basico("Marcos Perez", "13am56jku"); 
		Usuario admin1 = new Basico("Martin Gomez", "880m56jku"); 
		Usuario basico4 = new Basico("Adela Perez", "1aam56jku"); 
		Usuario basico5 = new Basico("Zaira Perez", "13am56jku"); 
		Usuario basico6 = new Basico("Florencia Perez", "13am56jku"); 
		Usuario admin2= new Basico("Wanda Gomez", "880m56jku"); 

		//agrego 4 usuarios
		
		software.agregarUsuario(basico1);
		software.agregarUsuario(admin2);
		software.agregarUsuario(basico2);
		software.agregarUsuario(basico3);
		software.agregarUsuario(admin1);
		software.agregarUsuario(basico4);
		software.agregarUsuario(basico5);
		software.agregarUsuario(basico6);

		
		assertTrue(software.ordenarPorNombreDeUsuario().first().equals(basico4));
        
	
	}
	
	
	@Test
	public void testQueUnaContraseniaInvalidaGenereExcepcion() throws InvalidPassword, UsuarioEnUsoException{
		
		BaseDeDatos software = new BaseDeDatos("System"); 
	
		Usuario basico1 = new Basico("Carla Perez", "Password123?"); 
		
		software.agregarUsuario(basico1);
	
		assertTrue(software.validarContrasenia(basico1)); 
        
	
	}
	
}

package ar.edu.unlam.tallerweb1.persistencia;

import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class TestUsuario extends SpringTest{
	
    @Inject
    private SessionFactory sessionFactory;
    
    @Test
    @Transactional
    @Rollback(true)
    
    public void testQueGeneraUnUsuarioYLoVerifica(){
    	
    	Usuario miUsuario = new Usuario();//generamos el usuario
    	miUsuario.setEmail("usuario@unlam.com");
    	miUsuario.setPassword("0000");
    	
    	getSession().save(miUsuario);//cargamos el usuario en la base de batos
    	
    	//Usuario usuarioDeTabla = getSession().get(Usuario.class, 1L); la L convierte el 1(id) en longtrae el usuario uno
    	Usuario usuarioDeTabla = getSession().get(Usuario.class, miUsuario.getId());//trae por id, usuarioDeTabla es el usuario dentro de la base de datos
    	assertThat(miUsuario.getEmail()).isEqualTo(usuarioDeTabla.getEmail());//comapra el usuario creado con el usuario ingresado en la base de datos
    	//assertThat verifica que el valor ingresado sea isEqualTo, isnot null, is null
    	assertThat(usuarioDeTabla).isNotNull();
    }
    
    @Test
    @Transactional
    @Rollback(true)
    
    public void testQueEliminaUsuario(){
    	
    	Usuario nuevoUsuario = new Usuario();
    	nuevoUsuario.setEmail("mail@mail.com");
    	nuevoUsuario.setPassword("1234");
    	
    	getSession().save(nuevoUsuario);
    	Usuario usuarioDeTabla = getSession().get(Usuario.class, nuevoUsuario.getId());//trae el usuario de la bd
    	assertThat(usuarioDeTabla).isNotNull();
    	
    	getSession().delete(usuarioDeTabla);//borra el usuario de la tabla
    	usuarioDeTabla = getSession().get(Usuario.class, nuevoUsuario.getId());
    	assertThat(usuarioDeTabla).isNull();
    }
    
    @Test
    @Transactional
    @Rollback(true)
    
    public void TestQueActualizaDatos(){
    	Usuario miUsuario = new Usuario();
    	miUsuario.setEmail("usuario@unlam.com");
    	miUsuario.setPassword("0000");
    	getSession().save(miUsuario);
    	
    	Usuario usuarioDeTabla = getSession().get(Usuario.class, miUsuario.getId());
    	assertThat(miUsuario.getEmail()).isEqualTo(usuarioDeTabla.getEmail());
    	
    	usuarioDeTabla.setEmail("nuevo@");
    	
    	getSession().update(usuarioDeTabla);//modifica el usuario de la tabla
    	usuarioDeTabla = getSession().get(Usuario.class, miUsuario.getId());
    	assertThat(usuarioDeTabla.getEmail()).isEqualTo("nuevo@");
    }
    
    
}

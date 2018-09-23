package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class TestTrabajoPracticoHibernate extends SpringTest{
    @Test
    @Transactional 
    @Rollback(true)
	
    public void testPaisesDeHablaInglesa(){
    	
    	Pais pais1 = new Pais();
    	pais1.setNombre("pais1");
    	pais1.setIdioma("ingles");
    	getSession().save(pais1);
    	
    	Pais pais2 = new Pais();
    	pais2.setNombre("pais2");
    	pais2.setIdioma("frances");
    	getSession().save(pais2);
    	
    	Pais pais3 = new Pais();
    	pais3.setNombre("pais3");
    	pais3.setIdioma("ingles");
    	getSession().save(pais3);
    	
    	Pais pais4 = new Pais();
    	pais4.setNombre("pais4");
    	pais4.setIdioma("castellano");
    	getSession().save(pais4);    	
    	List <Pais> miLista = getSession()
    			.createCriteria(Pais.class)
    			.add(Restrictions.like("idioma","ingles")).list();
    	
    	assertThat(miLista).hasSize(2);
    }
    
    @Test
    @Transactional 
    @Rollback(true)
    
    public void testPaisesDelContinenteEuropeo(){
    	
    	Continente continente1 = new Continente();
    	continente1.setNombre("europa");
    	
    	Continente continente2 = new Continente();
    	continente2.setNombre("america");
    	
    	Pais pais1 = new Pais();
    	pais1.setNombre("pais1");
    	pais1.setIdioma("ingles");
    	pais1.setContinente(continente1);
    	getSession().save(pais1);
    	
    	Pais pais2 = new Pais();
    	pais2.setNombre("pais2");
    	pais2.setIdioma("frances");
    	pais2.setContinente(continente2);
    	getSession().save(pais2);
    	
    	Pais pais3 = new Pais();
    	pais3.setNombre("pais3");
    	pais3.setIdioma("ingles");
    	pais3.setContinente(continente2);
    	getSession().save(pais3);
    	
    	Pais pais4 = new Pais();
    	pais4.setNombre("pais4");
    	pais4.setIdioma("castellano");
    	pais4.setContinente(continente1);
    	getSession().save(pais4);
    	
    	
    	List <Pais> miLista2 = getSession()
    			.createCriteria(Pais.class)
    			.add(Restrictions.like("continente","europa")).list();
    	
    	assertThat(miLista2).hasSize(2);
    }
    
    @Test
    @Transactional 
    @Rollback(true)
    
    public void testPaisesConCapitalAlNorteDelTropico(){
    	
    	Pais pais1 = new Pais();
    	pais1.setNombre("pais1");
    	
    	Pais pais2 = new Pais();
    	pais2.setNombre("pais2");

    	Pais pais3 = new Pais();
    	pais3.setNombre("pais3");
    	  	
    	Ubicacion ubicacion1 = new Ubicacion();
    	ubicacion1.setLatitud(5);
    	ubicacion1.setLongitud(5);
    	
    	Ubicacion ubicacion2 = new Ubicacion();
    	ubicacion2.setLatitud(4);
    	ubicacion2.setLongitud(5);
    	
    	Ubicacion ubicacion3 = new Ubicacion();
    	ubicacion3.setLatitud(7);
    	ubicacion3.setLongitud(6);
    	
    	Ciudad ciudad1 = new Ciudad();
    	ciudad1.setNombre("mexico");
    	ciudad1.setPais(pais1);
    	ciudad1.setUbicacionGeografica(ubicacion1);
    	getSession().save(ciudad1);

    	Ciudad ciudad2 = new Ciudad();
    	ciudad2.setNombre("buenosAires");
    	ciudad2.setPais(pais2);
    	ciudad2.setUbicacionGeografica(ubicacion2);
    	getSession().save(ciudad2);
    	
    	Ciudad ciudad3 = new Ciudad();
    	ciudad3.setNombre("Washington");
    	ciudad3.setPais(pais3);
    	ciudad3.setUbicacionGeografica(ubicacion3);
    	getSession().save(ciudad3);
    	
    	    	
    	List <Pais> miLista3 = getSession().createCriteria(Pais.class)
    			.createAlias("capital","capitalJoin")
    			.createAlias("capitalJoin.ubicacionGeografica","capBuscada")
    			.add(Restrictions.gt("latitud",5))
    			.add(Restrictions.gt("longitud",5)).list();
    	
    	assertThat(miLista3).hasSize(1);
    }
}

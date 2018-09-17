package ar.edu.unlam.tallerweb1.persistencia;


import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Empresa;

public class TestEmpresa extends SpringTest {

    @Test
    @Transactional
    @Rollback(true)
    
    public void testQueCreaUnaEmpresaConDireccion(){
    	
    	Direccion miDireccion = new Direccion();
    	miDireccion.setCalle("falsa");
    	miDireccion.setNumero(1234);
 //   	getSession().save(miDireccion);
    	
    	Empresa miEmpresa = new Empresa();
    	miEmpresa.setDireccion(miDireccion);
    	miEmpresa.setRazonSocial("nuevaEmpresa");
    	miEmpresa.setTelefono(12345678);
    	getSession().save(miEmpresa);
    	
    	Empresa nuevaEmpresa = getSession().get(Empresa.class,miEmpresa.getId());
    	
    	assertThat(miEmpresa.getDireccion()).isEqualTo(nuevaEmpresa.getDireccion());
    }
    
    

}

package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer longitud;
	private Integer latitud;
	
	public Integer getLongitud() {
		return longitud;
	}
	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}
	public Integer getLatitud() {
		return latitud;
	}
	public void setLatitud(Integer latitud) {
		this.latitud = latitud;
	}
	
	
	
}

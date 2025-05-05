package mx.com.metlife.reservas.main.domain;


import java.util.Date;
import java.util.List;

public class HistorialCapa {
	
	private String servicio;
	
	private Date fechaOperacion;
	
	private List<ServicioMap> capas;

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return the capas
	 */
	public List<ServicioMap> getCapas() {
		return capas;
	}

	/**
	 * @param capas the capas to set
	 */
	public void setCapas(List<ServicioMap> capas) {
		this.capas = capas;
	}
	
	

}

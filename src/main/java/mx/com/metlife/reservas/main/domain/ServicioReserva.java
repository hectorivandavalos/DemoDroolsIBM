package mx.com.metlife.reservas.main.domain;

import org.joda.time.LocalDate;

public class ServicioReserva {
	
	private int clave;
	
	private int motivo;
	
	private long servicio;
	
	private LocalDate fechaIni;
	
	private LocalDate fechaOperacion;
	
	private int nmpoliza;

	/**
	 * @return the clave
	 */
	public int getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(int clave) {
		this.clave = clave;
	}

	/**
	 * @return the motivo
	 */
	public int getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(int motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the servicio
	 */
	public Long getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Long servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the fechaIni
	 */
	public LocalDate getFechaIni() {
		return fechaIni;
	}

	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaOperacion
	 */
	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return the nmpoliza
	 */
	public int getNmpoliza() {
		return nmpoliza;
	}

	/**
	 * @param nmpoliza the nmpoliza to set
	 */
	public void setNmpoliza(int nmpoliza) {
		this.nmpoliza = nmpoliza;
	}
	
	

}

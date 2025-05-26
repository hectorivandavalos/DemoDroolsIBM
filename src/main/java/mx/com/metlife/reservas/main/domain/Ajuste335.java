package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class Ajuste335 {
	
	private LocalDate fechaRva;
	private BigDecimal importeRva;
	private int tipoFondo;
	private int tipoReserva;
	/**
	 * @return the fechaRva
	 */
	public LocalDate getFechaRva() {
		return fechaRva;
	}
	/**
	 * @param fechaRva the fechaRva to set
	 */
	public void setFechaRva(LocalDate fechaRva) {
		this.fechaRva = fechaRva;
	}
	/**
	 * @return the importeRva
	 */
	public BigDecimal getImporteRva() {
		return importeRva;
	}
	/**
	 * @param importeRva the importeRva to set
	 */
	public void setImporteRva(BigDecimal importeRva) {
		this.importeRva = importeRva;
	}
	/**
	 * @return the tipoFondo
	 */
	public int getTipoFondo() {
		return tipoFondo;
	}
	/**
	 * @param tipoFondo the tipoFondo to set
	 */
	public void setTipoFondo(int tipoFondo) {
		this.tipoFondo = tipoFondo;
	}
	/**
	 * @return the tipoReserva
	 */
	public int getTipoReserva() {
		return tipoReserva;
	}
	/**
	 * @param tipoReserva the tipoReserva to set
	 */
	public void setTipoReserva(int tipoReserva) {
		this.tipoReserva = tipoReserva;
	}
	
	
	

}

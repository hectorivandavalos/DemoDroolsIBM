package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class PrimaDeposito {
	
	private LocalDate fecha;
	private BigDecimal prima;
	private int tipoFondo;
	private int tipoReserva;
	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the prima
	 */
	public BigDecimal getPrima() {
		if(this.prima == null){
			return BigDecimal.ZERO;
		}
		return prima;
	}
	/**
	 * @param prima the prima to set
	 */
	public void setPrima(BigDecimal prima) {
		this.prima = prima;
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

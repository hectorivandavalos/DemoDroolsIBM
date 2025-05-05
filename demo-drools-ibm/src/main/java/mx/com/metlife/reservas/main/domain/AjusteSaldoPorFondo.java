package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

/**
 * Ajuste 305 y 315
 * @author roddx
 *
 */
public class AjusteSaldoPorFondo{
	
	private int concepto;
	
	private int tipoReserva;
	
	private int tipoFondo;
	
	private BigDecimal importe;
	
	private BigDecimal saldo;
	
	private LocalDate fecha;

	/**
	 * @return the concepto
	 */
	public int getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(int concepto) {
		this.concepto = concepto;
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
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

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
	
	

}

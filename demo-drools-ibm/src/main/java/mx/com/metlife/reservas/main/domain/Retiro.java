package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

public class Retiro {

	private long numServicio;
	
	private int cveServicio;
	
	private LocalDate fechaRetiro;
	
	private BigDecimal importe;
	
	private BigDecimal interes;
	
	private BigDecimal ajusteInteres;
	
	private Integer tipoFondo;
	
	//1=retiro, 2=cancelacion retiro
	private Integer tipoOperacion;
	
	private Integer tipoReserva;
	
	private BigDecimal importeMax;
	
	private Integer numPoliza;

	private String tabla;
	

	/**
	 * @return the numServicio
	 */
	public long getNumServicio() {
		return numServicio;
	}

	/**
	 * @param numServicio the numServicio to set
	 */
	public void setNumServicio(long numServicio) {
		this.numServicio = numServicio;
	}

	/**
	 * @return the cveServicio
	 */
	public int getCveServicio() {
		return cveServicio;
	}

	/**
	 * @param cveServicio the cveServicio to set
	 */
	public void setCveServicio(int cveServicio) {
		this.cveServicio = cveServicio;
	}

	/**
	 * @return the fechaRetiro
	 */
	public LocalDate getFechaRetiro() {
		return fechaRetiro;
	}

	/**
	 * @param fechaRetiro the fechaRetiro to set
	 */
	public void setFechaRetiro(LocalDate fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
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
	 * @return the tipoReserva
	 */
	public Integer getTipoReserva() {
		return tipoReserva;
	}

	/**
	 * @param tipoReserva the tipoReserva to set
	 */
	public void setTipoReserva(Integer tipoReserva) {
		this.tipoReserva = tipoReserva;
	}

	/**
	 * @return the tipoFondo
	 */
	public Integer getTipoFondo() {
		return tipoFondo;
	}

	/**
	 * @param tipoFondo the tipoFondo to set
	 */
	public void setTipoFondo(Integer tipoFondo) {
		this.tipoFondo = tipoFondo;
	}

	public Integer getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Integer tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	/**
	 * @return the ajusteInteres
	 */
	public BigDecimal getAjusteInteres() {
		return ajusteInteres;
	}

	/**
	 * @param ajusteInteres the ajusteInteres to set
	 */
	public void setAjusteInteres(BigDecimal ajusteInteres) {
		this.ajusteInteres = ajusteInteres;
	}


	/**
	 * @return the importeMax
	 */
	public BigDecimal getImporteMax() {
		return importeMax;
	}

	/**
	 * @param importeMax the importeMax to set
	 */
	public void setImporteMax(BigDecimal importeMax) {
		this.importeMax = importeMax;
	}

	/**
	 * @return the numPoliza
	 */
	public Integer getNumPoliza() {
		return numPoliza;
	}

	/**
	 * @param numPoliza the numPoliza to set
	 */
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	
	
	
	public String getTabla() {
		return StringUtils.trim(tabla);
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
}

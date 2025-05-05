package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import mx.com.metlife.commons.util.AppConstants;

public class Pago {
	
	private String poliza;
	
	private Integer serie;
	
	private BigDecimal importe;
	
	private BigDecimal porcentaje;
	
	private LocalDate fechaRecibo;
	
	private LocalDate fechaPago;
	
	private Integer numPago;
	
	private Integer quincena;
	
	private Integer aaaamm;
	
	private String tipoMov;
	
	private boolean alterno;

	private boolean contempladoParaMensual;
	
	private BigDecimal importeAlterno;
	
	private Boolean ajusteCorreccionDatos = null;
	
	private Integer eliminarPagoSobrante = null;
	
	private BigDecimal calculoDifPrimas = null;
	
	private BigDecimal calculoDifPrimasExc = null;
	
	private BigDecimal primaTotalRec;
	
	private BigDecimal primaExcRec;

	/**
	 * @return the poliza
	 */
	public String getPoliza() {
		return poliza;
	}

	/**
	 * @param poliza the poliza to set
	 */
	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	/**
	 * @return the serie
	 */
	public Integer getSerie() {
		return serie;
	}

	/**
	 * @param serie the serie to set
	 */
	public void setSerie(Integer serie) {
		this.serie = serie;
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
	 * @return the porcentaje
	 */
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * @return the fechaRecibo
	 */
	public LocalDate getFechaRecibo() {
		return fechaRecibo;
	}

	/**
	 * @param fechaRecibo the fechaRecibo to set
	 */
	public void setFechaRecibo(LocalDate fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	/**
	 * @return the fechaPago
	 */
	public LocalDate getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public BigDecimal getPorcentajeDec(){
	
		return this.porcentaje.divide(AppConstants.CIEN);
	}

	public Integer getNumPago() {
		return numPago;
	}

	public void setNumPago(Integer numPago) {
		this.numPago = numPago;
	}

	public Integer getQuincena() {
		return quincena;
	}

	public void setQuincena(Integer quincena) {
		this.quincena = quincena;
	}

	/**
	 * @return the tipoMov
	 */
	public String getTipoMov() {
		return tipoMov;
	}

	/**
	 * @param tipoMov the tipoMov to set
	 */
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	
	
	public int getMes(){
		return this.fechaPago.getMonthOfYear();
	}

	/**
	 * @return the alterno
	 */
	public boolean isAlterno() {
		return alterno;
	}

	/**
	 * @param alterno the alterno to set
	 */
	public void setAlterno(boolean alterno) {
		this.alterno = alterno;
	}

	/**
	 * @return the importeAlterno
	 */
	public BigDecimal getImporteAlterno() {
		return importeAlterno;
	}

	/**
	 * @param importeAlterno the importeAlterno to set
	 */
	public void setImporteAlterno(BigDecimal importeAlterno) {
		this.importeAlterno = importeAlterno;
	}

	/**
	 * @return the ajusteCorreccionDatos
	 */
	public Boolean getAjusteCorreccionDatos() {
		return ajusteCorreccionDatos;
	}

	/**
	 * @param ajusteCorreccionDatos the ajusteCorreccionDatos to set
	 */
	public void setAjusteCorreccionDatos(Boolean ajusteCorreccionDatos) {
		this.ajusteCorreccionDatos = ajusteCorreccionDatos;
	}

	/**
	 * @return the eliminarPagoSobrante
	 */
	public Integer getEliminarPagoSobrante() {
		return eliminarPagoSobrante;
	}

	/**
	 * @param eliminarPagoSobrante the eliminarPagoSobrante to set
	 */
	public void setEliminarPagoSobrante(Integer eliminarPagoSobrante) {
		this.eliminarPagoSobrante = eliminarPagoSobrante;
	}

	/**
	 * @return the calculoDifPrimas
	 */
	public BigDecimal getCalculoDifPrimas() {
		return calculoDifPrimas;
	}

	/**
	 * @param calculoDifPrimas the calculoDifPrimas to set
	 */
	public void setCalculoDifPrimas(BigDecimal calculoDifPrimas) {
		this.calculoDifPrimas = calculoDifPrimas;
	}

	/**
	 * @return the calculoDifPrimasExc
	 */
	public BigDecimal getCalculoDifPrimasExc() {
		return calculoDifPrimasExc;
	}

	/**
	 * @param calculoDifPrimasExc the calculoDifPrimasExc to set
	 */
	public void setCalculoDifPrimasExc(BigDecimal calculoDifPrimasExc) {
		this.calculoDifPrimasExc = calculoDifPrimasExc;
	}

	public boolean isContempladoParaMensual() {
		return contempladoParaMensual;
	}

	public void setContempladoParaMensual(boolean contempladoParaMensual) {
		this.contempladoParaMensual = contempladoParaMensual;
	}
	
	
	public Integer getAaaamm() {
		
		if( this.aaaamm == null && this.quincena != null){
		     this.aaaamm = ((this.quincena / 100) * 100) + (int) Math.ceil(  (this.quincena % 100) / 2d );
		}
		
		
		
		return aaaamm;
	}

	/**
	 * @return the primaTotalRec
	 */
	public BigDecimal getPrimaTotalRec() {
		return primaTotalRec;
	}

	/**
	 * @param primaTotalRec the primaTotalRec to set
	 */
	public void setPrimaTotalRec(BigDecimal primaTotalRec) {
		this.primaTotalRec = primaTotalRec;
	}

	/**
	 * @return the primaExcRec
	 */
	public BigDecimal getPrimaExcRec() {
		return primaExcRec;
	}

	/**
	 * @param primaExcRec the primaExcRec to set
	 */
	public void setPrimaExcRec(BigDecimal primaExcRec) {
		this.primaExcRec = primaExcRec;
	}
	
	
	
}

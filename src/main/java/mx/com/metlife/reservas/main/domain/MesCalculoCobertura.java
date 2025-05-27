package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class MesCalculoCobertura {
	
	protected LocalDate mes;
	
	protected ServicioMap servicio;
	
	protected CoberturaMap cobertura;
	
	protected String plantilla;
	
	protected String claveCobertura;
	
	protected String claveServicio;
	
	protected int numeroMes;
	
	protected Map<String, Object> campos;
	
	protected boolean calculado;
	
	public MesCalculoCobertura(LocalDate mes, int numeroMes){
		campos = new HashMap<String,Object>();
		this.mes = mes;
		this.numeroMes = numeroMes;
	}

	public LocalDate getDateMesFin() {
		return mes.plusMonths(1);
	}

	public LocalDate getDateMesIni() {
		return mes;
	}	
	

	public LocalDate getMes() {
		return mes;
	}

	public void setMes(LocalDate mes) {
		this.mes = mes;
	}

	public ServicioMap getServicio() {
		return servicio;
	}

	public void setServicio(ServicioMap servicio) {
		this.servicio = servicio;
	}

	public CoberturaMap getCobertura() {
		return cobertura;
	}

	public void setCobertura(CoberturaMap cobertura) {
		this.cobertura = cobertura;
	}

	public boolean isCalculado() {
		return calculado;
	}

	public void setCalculado(boolean calculado) {
		this.calculado = calculado;
	}

	public Map<String, Object> getCampos() {
		return campos;
	}

	public void setCampos(Map<String, Object> campos) {
		this.campos = campos;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public int getNumeroMes() {
		return numeroMes;
	}

	public void setNumeroMes(int numeroMes) {
		this.numeroMes = numeroMes;
	}

	public String getClaveCobertura() {
		return claveCobertura;
	}

	public void setClaveCobertura(String claveCobertura) {
		this.claveCobertura = claveCobertura;
	}

	public String getClaveServicio() {
		return claveServicio;
	}

	public void setClaveServicio(String claveServicio) {
		this.claveServicio = claveServicio;
	}

	public void setValue(String campo, Object value){
		this.campos.put(campo, value);
	}
	
	public Map<String, Object> getC(){
		return this.campos;
	}
	
	public String cString(String k){
		return (String) this.campos.get(k);
	}
	
	public LocalDate cDate(String k){
		return (LocalDate) this.campos.get(k);
	}
	

	public Integer cInt(String k){
		return (Integer) this.campos.get(k);
	}
	
	public Integer cIntDef(String k){
		if (this.campos.get(k) == null) {
			return 0;
		}
		return (Integer) this.campos.get(k);
	}
	
	
	public BigDecimal cDec(String k){
		return (BigDecimal) this.campos.get(k);
	}
	
	public BigDecimal cDecDef(String k){
		if(this.campos.get(k) == null){
			return BigDecimal.ZERO;
		}
		return (BigDecimal) this.campos.get(k);
	}
	
	public double cDouble(String k){
		return ((BigDecimal) this.campos.get(k)).doubleValue();
	}
	
}

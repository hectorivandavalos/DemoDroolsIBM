package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class MesCalculo {
	
	private LocalDate mes;
	
	private int numeroMes;
	
	private Map<String, Object> campos;
	
	
	public MesCalculo(LocalDate mes, int numeroMes){
		campos = new HashMap<String,Object>();
		this.mes = mes;
		this.numeroMes = numeroMes;
	}
	
	
	
	public int getNumeroMes() {
		return numeroMes;
	}

	public void setNumeroMes(int numeroMes) {
		this.numeroMes = numeroMes;
	}

	public LocalDate getMes() {
		return mes;
	}

	public void setMes(LocalDate mes) {
		this.mes = mes;
	}

	public Map<String, Object> getC() {
		return campos;
	}

	public void setC(Map<String, Object> c) {
		this.campos = c;
	}
	
	
	public void setValue(String campo, Object value){
		this.campos.put(campo, value);
	}
	
	public void removeValue(String campo){
		this.campos.remove(campo);
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MesCalculo [mes=");
		builder.append(mes);
		builder.append(", numeroMes=");
		builder.append(numeroMes);
		builder.append("]");
		return builder.toString();
	}

	
	
	public LocalDate getDateMesFin() {
		return mes.plusMonths(1);
	}

	public LocalDate getDateMesIni() {
		return mes;
	}

	
	
}

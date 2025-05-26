package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class SobranteDetalleMap extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8491748077475156192L;
	
	public SobranteDetalleMap(Map<String, Object> map) {
		this.putAll(map);
	}
	
	public String getKey() {		
		StringBuffer buf = new StringBuffer();
		buf.append(this.get("POLIZA").toString());
		buf.append("-");
		buf.append(this.get("NUM_PAGO").toString());
		buf.append("-");
		buf.append(this.get("NUMSERVICIO").toString());
		buf.append("-");
		buf.append(this.get("ID_COBERTURA").toString());
		return buf.toString();
	}
	
	public String info() {
		StringBuffer buf = new StringBuffer();
		buf.append(" [ ");
		buf.append(this.cDec("NUMSERVICIO"));
		buf.append(" | ");
		buf.append(this.cDec("NUM_PAGO"));
		buf.append(" | ");
		buf.append(this.cString("ID_COBERTURA"));
		buf.append(" ] ");
		return buf.toString();
	}
	
	public String cString(String k){
		return (String) this.get(k);
	}
	
	
	public LocalDate cDate(String k){
		try{
			return LocalDate.fromDateFields((Date)this.get(k));
		}catch(Exception e){
			return (LocalDate) this.get(k);
		}
	}
		
	public Integer cInt(String k){
		try{
			return ((BigDecimal) this.get(k)).intValue();
		}catch(Exception e){
			return (Integer) this.get(k);
		}
	}
	
	public BigDecimal cDec(String k){
		try{
			return new BigDecimal((Double) this.get(k));
		}catch(Exception e){
			return (BigDecimal) this.get(k);
		}
	}
	
	public BigDecimal cDecDef(String k){
		try{
			return new BigDecimal((Double) this.get(k));
		}catch(Exception e){
			if (this.get(k) == null) {
				return BigDecimal.ZERO;
			}
			return (BigDecimal) this.get(k);
		}
	}
	
	public Long cLong(String k){
		try{
			return ((Long) this.get(k)).longValue();
		}catch(Exception e){
			return (Long) this.get(k);
		}
	}
}

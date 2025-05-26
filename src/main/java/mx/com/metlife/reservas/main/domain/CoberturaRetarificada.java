package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class CoberturaRetarificada extends HashMap<String, Object>{
	
	
	public CoberturaRetarificada(Map<String, Object> map){
		this.putAll(map);
	}
	
	public String getKey() {
		return (String) this.get("CVE_COBERTURA");
	}
	
	public String info() {
		StringBuffer buf = new StringBuffer();
		buf.append(" [ ");
		buf.append(this.cInt("NUMSERVICIO_ORIG"));
		buf.append(" | ");
		buf.append(this.getKey());
		buf.append(" | ");
		buf.append(String.format("%.2f", this.cDec("SUMA_ASEGURADA")));
		buf.append(" | ");
		buf.append(String.format("%.2f", this.cDec("PRIMA")));
		buf.append(" ] ");
		return buf.toString();
	}
	
	public String cString(String k){
		return (String) this.get(k);
	}
	
	public LocalDate cDate(String k){
		return LocalDate.fromDateFields((Date)this.get(k));
	}
		
	public Integer cInt(String k){
		try{
			return ((BigDecimal) this.get(k)).intValue();
		}catch(Exception e){
			return (Integer) this.get(k);
		}
	}
	
	public Integer cIntDef(String k){
		try{
			if(this.get(k) == null){
				return 0;
			}
			return ((BigDecimal) this.get(k)).intValue();
		}catch(Exception e){
			try{
				return (Integer) this.get(k);
			}catch(Exception ex){
				return 0;
			}
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
	
	public Double cDouble(String k){
		return (Double) this.get(k);
	}
	
}

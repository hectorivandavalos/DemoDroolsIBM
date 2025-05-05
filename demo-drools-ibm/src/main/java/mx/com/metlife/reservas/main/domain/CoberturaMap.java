package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.kie.api.definition.type.PropertyReactive;

//@PropertyReactive
public class CoberturaMap extends HashMap<String, Object>{
	
	
	private boolean retarificar = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4193340767936655167L;
	
	public CoberturaMap(){
		
	}
	
	public CoberturaMap(Map<String, Object> map){
		this.putAll(map);
		this.put("FACTOR_SA_RETR", BigDecimal.ONE);
		this.put("FACTOR_FRAG_COMI", BigDecimal.ONE);
		this.put("#sumaAseguradaOrig", this.get("SUMA_ASEGURADA"));
		this.put("#primaOrig", this.get("PRIMA"));
	}
	
	public String getKey() {
		return (String) this.get("CVE_COBERTURA");
	}
	
	public String info() {
		StringBuffer buf = new StringBuffer();
		buf.append(" [ ");
		buf.append(this.cInt("NUM_SERVICIO"));
		buf.append(" | ");
		buf.append(this.getKey());
		buf.append(" | ");
		buf.append(String.format("%.2f", this.cDec("SUMA_ASEGURADA")));
		buf.append(" | ");
		buf.append(String.format("%.2f", this.cDec("PRIMA")));
		buf.append(" ] ");
		return buf.toString();
	}
	
	public String clave() {
		StringBuffer buf = new StringBuffer();
		buf.append(" [ ");
		buf.append(this.cInt("NUM_SERVICIO"));
		buf.append(" | ");
		buf.append(this.getKey());
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
	
	public String concat(String c1, String c2){
		return this.cString(c1) + this.cString(c2);
	}

	public boolean isRetarificar() {
		return retarificar;
	}

	public void setRetarificar(boolean retarificar) {
		this.retarificar = retarificar;
	}

	

}

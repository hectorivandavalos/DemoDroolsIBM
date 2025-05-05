package mx.com.metlife.reservas.main.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

public class ServicioMap extends HashMap<String, Object>{
	
	
    private PolizaMap poliza;
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 567830086168166817L;
	
	public ServicioMap(Map<String, Object> servicio){
		this.put("COBERTURAS_AFECTADAS", new HashSet<CoberturaMap>());
		this.putAll(servicio);
		this.put("COBERTURAS", new LinkedHashMap<String, CoberturaMap>());
		
	}
	
	

	public String getKey() {
		return String.valueOf(this.get("NUM_SERVICIO"));
	}
	
	public void addCobertura(CoberturaMap cobertura){
		this.propagaDatosCobertura(cobertura);
		this.getCoberturas().put(cobertura.getKey(), cobertura);
	}
	
public CoberturaMap addCoberturaAfectada(CoberturaMap cobertura){
		CoberturaMap copy = new CoberturaMap(cobertura);
		for(CoberturaMap c : this.getCoberturasAfectadas()){
			if(c.cInt("NUM_SERVICIO").equals(cobertura.cInt("NUM_SERVICIO")) && c.getKey().equals(cobertura.getKey())){
				return copy;
			}
		}
		this.getCoberturasAfectadas().add(copy);
		
		return copy;
	}
	
	
	public CoberturaMap addCoberturaAfectada(CoberturaMap cobertura, ServicioMap serv){
		CoberturaMap copy = new CoberturaMap(cobertura);
		for(CoberturaMap c : this.getCoberturasAfectadas()){
			if(c.cInt("NUM_SERVICIO").equals(cobertura.cInt("NUM_SERVICIO")) && c.getKey().equals(cobertura.getKey())){
				return copy;
			}
		}
		
		if(serv.get("#capaPositiva") != null){
			this.getCoberturasAfectadas().add(copy);
		}
		
		return copy;
	}

	public Map<String, CoberturaMap> getCoberturas() {
		return (Map<String, CoberturaMap>) this.get("COBERTURAS");
	}
	
	public Set<CoberturaMap> getCoberturasAfectadas() {
		return (Set<CoberturaMap>) this.get("COBERTURAS_AFECTADAS");
	}
	
	public List<CoberturaMap> getCoberturasAfectadasList() {
		List<CoberturaMap> list = new ArrayList<CoberturaMap>();
		for(CoberturaMap m : (Set<CoberturaMap>) this.get("COBERTURAS_AFECTADAS")){
			list.add(m);
		}
		
		Collections.sort(list, new Comparator<CoberturaMap>() {
		    public int compare(CoberturaMap a, CoberturaMap b) {
		        return new Integer(b.cInt("NUM_SERVICIO")).compareTo( new Integer(a.cInt("NUM_SERVICIO")));
		    }
		});

		return list;
	}

	public void addCoberturas(List<CoberturaMap> coberturas) {
		if(coberturas != null) {
			for (CoberturaMap cobertura : coberturas) {
				this.propagaDatosCobertura(cobertura);
				this.addCobertura(cobertura);
			}
		}
	}
	
	public CoberturaMap getCobertura(String key){
		return this.getCoberturas().get(key);
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
	
	public Integer cIntDef(String k){
		try{
			return cInt(k);
		}catch(Exception e){
			return 0;
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
	
	public Double cDouble(String k){
		return (Double) this.get(k);
	}
	
	public String concat(String c1, String c2){
		return this.cString(c1) + this.cString(c2);
	}
	
	private void propagaDatosCobertura(CoberturaMap cobertura){
		cobertura.put("ID_SERV", this.get("ID_SERV"));
		cobertura.put("ID_SERV_ANT", this.get("ID_SERV_ANT"));
		cobertura.put("CVE_SERVICIO", this.get("CVE_SERVICIO"));
		cobertura.put("PLAN", this.get("PLAN"));
		cobertura.put("MOTIVO", this.get("MOTIVO"));
		cobertura.put("SERVICIO_MOTIVO", this.get("CVE_SERVICIO") + "-" + this.get("MOTIVO"));
		cobertura.put("COB_SERVICIO_MOTIVO", cobertura.get("CVE_COBERTURA") +"-" +cobertura.get("SERVICIO_MOTIVO"));
		
	}



	/**
	 * @return the poliza
	 */
	public PolizaMap getPoliza() {
		return poliza;
	}



	/**
	 * @param poliza the poliza to set
	 */
	public void setPoliza(PolizaMap poliza) {
		this.poliza = poliza;
	}
	

	
	
}

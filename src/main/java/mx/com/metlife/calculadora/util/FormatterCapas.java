package mx.com.metlife.calculadora.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

import mx.com.metlife.reservas.main.domain.CoberturaMap;
import mx.com.metlife.reservas.main.domain.PolizaMap;
import mx.com.metlife.reservas.main.domain.ServicioMap;


public class FormatterCapas {
	
			
	public static void print(String titulo, PolizaMap poliza, LocalDate fecha){
		
			List<String> coberturas = new ArrayList<String>();
			
			for(Map.Entry<String, ServicioMap> serv : poliza.getServicios().entrySet()){
				ServicioMap servicio = serv.getValue();
				for(Map.Entry<String, CoberturaMap> cob : servicio.getCoberturas().entrySet()){
					if(!coberturas.contains(cob.getValue().getKey())){
						coberturas.add(cob.getValue().getKey());
					}
				}
			}
			
			Collections.sort(coberturas);
			
			int sizeLine = 145 + 85 * coberturas.size();
			StringBuilder out = new StringBuilder();
			out.append("\n");
			out.append(titulo).append("\n");
			out.append(StringUtils.repeat("-", sizeLine)).append("\n");
			out.append(String.format("%145s", ""));
			
			for(String cob : coberturas){
				out.append(String.format("%-85s", StringUtils.center(cob, 65)));	
			}
			
			out.append("\n");
			
			out.append(String.format("%50s%5s%5s%5s%5s%15s%15s%15s%15s%15s", StringUtils.center("SERVICIO",30),"CVE","MOT","PLAN", "PFT", "FECHA INI VIG", "FECHA OPERA","PRIMA COB","PRIMA EXC", "|"));
			addCoberturaData(out, coberturas.size());
			out.append("\n");
			
			out.append(StringUtils.repeat("-", sizeLine)).append("\n");
			
			System.out.println(out.toString());
			
			for(Map.Entry<String, ServicioMap> serv : poliza.getServicios().entrySet()){
				
				out.delete(0, out.length());
				
				ServicioMap servicio = serv.getValue();
				
				if(fecha != null && fecha.isBefore(LocalDate.fromDateFields((Date)servicio.get("FEC_INI_VIG")))){
					break;
				}
				
				
				out.append(String.format("%-10s", servicio.get("NUM_SERVICIO")));
				out.append(String.format("%-40s", servicio.get("MOVIMIENTO")));
				out.append(String.format("%5s",  servicio.get("CVE_SERVICIO")));
				out.append(String.format("%5s",  servicio.get("MOTIVO")));
				out.append(String.format("%5s",  servicio.get("PLAN")));
				out.append(String.format("%5s",  servicio.get("DEV_PRIMA")));
				out.append(String.format("%15s", servicio.cDate("FEC_INI_VIG")));
				out.append(String.format("%15s", servicio.cDate("FEC_OPERACION")));
				out.append(String.format("%15s", String.format("%.2f", servicio.cDec("PRIMA_COBRO"))));
				out.append(String.format("%15s", String.format("%.2f", servicio.cDec("PRIMA_EXC"))));
				out.append(String.format("%15s", "|"));
				
				if(StringUtils.equals("A", servicio.cString("STATUS"))){
					for(String cob : coberturas){
						out.append(String.format("%85s","|"));	
					}
				}else{
					for(String cob : coberturas){
						CoberturaMap vbas = servicio.getCobertura(cob);
						out.append(datosCobertura(vbas));	
					}
				}
				
				System.out.println(out.toString());
				//out.append("\n");
			}
			out.delete(0, out.length());
			out.append(StringUtils.repeat("-", sizeLine)).append("\n");
			out.append("\n");
		    System.out.println(out.toString());
			//logger.info(out.toString());
		
	}
	
	public static void print(String titulo, PolizaMap poliza){
		FormatterCapas.print(titulo, poliza, null);
	}
	
	
	
	private static void addCoberturaData(StringBuilder headers, int n){
		for(int i=0; i< n; i++){
			headers.append(String.format("%15s%10s%10s%10s%10s%5s%5s%5s%10s%5s", "SUMA ASEGURADA", "PRIMA", "MEDICA", "OCUP", "BIT", "EDAD","SEXO","HAB", "ESTATUS","|"));
		}
	}
	
	private static String datosCobertura(CoberturaMap cob){
		if(cob == null){
			return String.format("%85s","|");
		}
		return String.format("%15s%10s%10s%10s%10s%5s%5s%5s%10s%5s", 
				String.format("%.2f", cob.get("SUMA_ASEGURADA")),
				String.format("%.2f",cob.get("PRIMA")), 
				String.format("%.2f",cob.get("MEDICO")), 
				String.format("%.2f",cob.get("OCUPACIONAL")), 
				String.format("%.2f",cob.get("PRIMA_BIT")),
				"VGFH".equals(cob.getKey()) ? cob.get("NUM_HIJOS") : cob.get("EDAD"),
				cob.get("SEXO"),
				cob.get("HABITO"),
				cob.get("STATUS"),
				"|"
				);
	}
	
	
	
	
	
	public static StringBuilder getCapasFormat(String titulo, PolizaMap poliza){
		
			List<String> coberturas = new ArrayList<String>();
			
			for(Map.Entry<String, ServicioMap> serv : poliza.getServicios().entrySet()){
				ServicioMap servicio = serv.getValue();
				for(Map.Entry<String, CoberturaMap> cob : servicio.getCoberturas().entrySet()){
					if(!coberturas.contains(cob.getValue().getKey())){
						coberturas.add(cob.getValue().getKey());
					}
				}
			}
			
			Collections.sort(coberturas);
			
			StringBuilder out = new StringBuilder();
			
			out.append(titulo).append("\n");
			
			out.append("NUM SERVICIO|DESCRIPCI�N|CVE SERVICIO|MOTIVO|FECHA INI VIG|PRIMA COB|PRIMA EXC|");
			addCoberturaDataCapas(out, coberturas.size());
			out.append("\n");
			
			for(Map.Entry<String, ServicioMap> serv : poliza.getServicios().entrySet()){
				
				ServicioMap servicio = serv.getValue();
				
				out.append(servicio.get("NUM_SERVICIO"));
				out.append("|");
				out.append(servicio.get("MOVIMIENTO"));
				out.append("|");
				out.append(servicio.get("CVE_SERVICIO"));
				out.append("|");
				out.append(servicio.get("MOTIVO"));
				out.append("|");
				out.append(servicio.cDate("FEC_INI_VIG"));
				out.append("|");
				out.append(String.format("%.2f", servicio.cDec("PRIMA_COBRO")));
				out.append("|");
				out.append(String.format("%.2f", servicio.cDec("PRIMA_EXC")));
				out.append("|");
				
				if(StringUtils.equals("A", servicio.cString("STATUS"))){
					for(String cob : coberturas){
						out.append("|");	
					}
				}else{
					for(String cob : coberturas){
						CoberturaMap vbas = servicio.getCobertura(cob);
						out.append(datosCoberturaCapas(cob, vbas));	
					}
				}
				
				out.append("\n");
			}
			out.append("\n");
			return out;
		
		}
	
	
		private static void addCoberturaDataCapas(StringBuilder headers, int n){
			for(int i=0; i< n; i++){
				headers.append("|COBERTURA|SUMA ASEGURADA|PRIMA|MEDICA|OCUP|BIT|ESTATUS|");
			}
		}
	
		private static String datosCoberturaCapas(String cve, CoberturaMap cob){
			if(cob == null){
				return String.format("|%s|||||||", cve);
			}
			return String.format("|%s|%s|%s|%s|%s|%s|%s|", 
					cob.getKey(),
					String.format("%.2f", cob.get("SUMA_ASEGURADA")),
					String.format("%.2f",cob.get("PRIMA")), 
					String.format("%.2f",cob.get("MEDICO")), 
					String.format("%.2f",cob.get("OCUPACIONAL")),
					String.format("%.2f",cob.get("PRIMA_BIT")),
					cob.get("STATUS")
					);
		}
	
	
	
	

}

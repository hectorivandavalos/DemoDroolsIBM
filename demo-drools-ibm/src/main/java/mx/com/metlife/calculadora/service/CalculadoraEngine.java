package mx.com.metlife.calculadora.service;

import java.util.Map;

import org.joda.time.LocalDate;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.metlife.calculadora.util.FormatterCapas;
import mx.com.metlife.reservas.main.domain.CoberturaMap;
import mx.com.metlife.reservas.main.domain.MesCalculo;
import mx.com.metlife.reservas.main.domain.MesCalculoCobertura;
import mx.com.metlife.reservas.main.domain.PolizaMap;
import mx.com.metlife.reservas.main.domain.ServicioMap;

@Service
public class CalculadoraEngine {
	
	@Autowired
	private KieContainer kieContainer;
	
	public void execute(PolizaMap poliza, LocalDate fechaFin) {
		
		KieSession kieSession = kieContainer.newKieSession();
		
		LocalDate mesIteracion = this.putSessionPolizaData(poliza, kieSession);
		
		FormatterCapas.print("CAPAS INICIALES", poliza);
		
		kieSession.getAgenda().getAgendaGroup("orquestador-analisis-capas").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("ajuste-valores").setFocus();
		kieSession.fireAllRules();
		
		int numeroMes = 1;
		
		/*while(mesIteracion.isBefore(fechaFin)) {
			
			kieSession.insert(mesIteracion);
			
			System.out.println("------------------------");
			System.out.println("MES "+mesIteracion);
			System.out.println("------------------------");
			
			kieSession.getAgenda().getAgendaGroup("orquestador-analisis-capas").setFocus();
			kieSession.fireAllRules();
			
			MesCalculo mesCalculo = new MesCalculo(mesIteracion, numeroMes);
			
			kieSession.insert(mesCalculo);
			
			for(Map.Entry<String, ServicioMap> servEntry : poliza.getServicios().entrySet()){
				
				ServicioMap servicio = servEntry.getValue();
				LocalDate fecIniServ = (LocalDate) servicio.get("FEC_INI_VIG");
									
				if(mesIteracion.isBefore(fecIniServ)){
					continue;
				}
				
				for(Map.Entry<String, CoberturaMap> cobEntry : servicio.getCoberturas().entrySet()){
					CoberturaMap cobertura = cobEntry.getValue();
					
					
					if("V".equals(cobertura.cString("STATUS")) && cobertura.cDec("SUMA_ASEGURADA").doubleValue() > 0 ) {
						MesCalculoCobertura mesCob = new MesCalculoCobertura(mesIteracion, numeroMes);
						mesCob.setCobertura(cobertura);
						mesCob.setServicio(servicio);
						mesCob.setClaveCobertura(cobertura.getKey());
						kieSession.insert(mesCob);	
					}
				}
			}
			
			kieSession.getAgenda().getAgendaGroup("coi").setFocus();
			kieSession.fireAllRules();
			
			kieSession.getAgenda().getAgendaGroup("sobrante").setFocus();
			kieSession.fireAllRules();
			
			mesIteracion = mesIteracion.plusMonths(1);
			numeroMes++;
		}*/
	
		
		FormatterCapas.print("CAPAS FINALES", poliza);
		
		kieSession.dispose();
		
	}
	
	
	private LocalDate putSessionPolizaData(PolizaMap poliza, KieSession session) {
		
		session.insert(poliza);
		
		LocalDate mesIteracion = null;
		for(Map.Entry<String, ServicioMap> serv : poliza.getServicios().entrySet()){
			ServicioMap servicio = serv.getValue();

			if(mesIteracion == null){
				mesIteracion = servicio.cDate("FEC_INI_VIG");
			}
			
			session.insert(servicio);
			for(Map.Entry<String, CoberturaMap> cob : servicio.getCoberturas().entrySet()){
				session.insert(cob.getValue());
			}
		}
		
		return mesIteracion;
	}	
}

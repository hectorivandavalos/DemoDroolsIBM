package mx.com.metlife.calculadora.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mx.com.metlife.reservas.main.domain.PolizaMap;

@Service
public class PolizaService {

	/*
	 * public PolizaMap createPoliza2() {
	 * 
	 * PolizaMap polizaMap = new PolizaMap();
	 * 
	 * ServicioMap emision = new ServicioMap();
	 * ServicioMap incremento = new ServicioMap();
	 * ServicioMap inclusion = new ServicioMap();
	 * ServicioMap cancelacion = new ServicioMap();
	 * ServicioMap incremento2 = new ServicioMap();
	 * 
	 * emision.put("NUM_SERVICIO", 1000);
	 * emision.put("CVE_SERVICIO", 0);
	 * emision.put("MOTIVO", 0);
	 * emision.put("MOVIMIENTO", "EMISION");
	 * emision.put("FEC_INI_VIG", new LocalDate(2004, 1, 1));
	 * 
	 * CoberturaMap vbas = new CoberturaMap();
	 * vbas.put("CVE_COBERTURA", "VBAS");
	 * vbas.put("PRIMA", new BigDecimal(500));
	 * vbas.put("EDAD", 30);
	 * vbas.put("SEXO", "M");
	 * vbas.put("HABITO", "S");
	 * vbas.put("PRIMA_BIT", new BigDecimal(5) );
	 * vbas.put("SUMA_ASEGURADA", new BigDecimal(100000));
	 * vbas.put("STATUS", "V");
	 * 
	 * CoberturaMap vbit = new CoberturaMap();
	 * vbit.put("CVE_COBERTURA", "VBIT");
	 * vbit.put("PRIMA", new BigDecimal(1));
	 * vbit.put("EDAD", 30);
	 * vbit.put("SEXO", "M");
	 * vbit.put("HABITO", "S");
	 * vbit.put("SUMA_ASEGURADA", new BigDecimal(1));
	 * vbit.put("STATUS", "V");
	 * 
	 * emision.addCobertura(vbas);
	 * emision.addCobertura(vbit);
	 * 
	 * 
	 * incremento.put("NUM_SERVICIO", 2000);
	 * incremento.put("CVE_SERVICIO", 2208);
	 * incremento.put("MOTIVO", 631);
	 * incremento.put("MOVIMIENTO", "INCREMENTO");
	 * incremento.put("FEC_INI_VIG", new LocalDate(2008, 5, 1));
	 * 
	 * vbas = new CoberturaMap(vbas);
	 * vbas.put("PRIMA", new BigDecimal(600));
	 * vbas.put("PRIMA_BIT", new BigDecimal(6) );
	 * vbas.put("SUMA_ASEGURADA", new BigDecimal(150000));
	 * 
	 * vbit = new CoberturaMap(vbit);
	 * vbit.put("PRIMA", new BigDecimal(2));
	 * vbit.put("SUMA_ASEGURADA", new BigDecimal(2));
	 * 
	 * incremento.addCobertura(vbas);
	 * incremento.addCobertura(vbit);
	 * 
	 * 
	 * inclusion.put("NUM_SERVICIO", 3000);
	 * inclusion.put("CVE_SERVICIO", 2214);
	 * inclusion.put("MOTIVO", 631);
	 * inclusion.put("MOVIMIENTO", "INCLUSION");
	 * inclusion.put("FEC_INI_VIG", new LocalDate(2015, 9, 1));
	 * 
	 * vbas = new CoberturaMap(vbas);
	 * vbas.put("PRIMA", new BigDecimal(600));
	 * vbas.put("SUMA_ASEGURADA", new BigDecimal(150000));
	 * 
	 * CoberturaMap vcat = new CoberturaMap();
	 * vcat.put("CVE_COBERTURA", "VCAT");
	 * vcat.put("PRIMA", new BigDecimal(200));
	 * vcat.put("PRIMA_BIT", new BigDecimal(2) );
	 * vcat.put("SUMA_ASEGURADA", new BigDecimal(50000));
	 * vcat.put("EDAD", 30);
	 * vcat.put("SEXO", "M");
	 * vcat.put("HABITO", "S");
	 * vcat.put("STATUS", "V");
	 * 
	 * vbit = new CoberturaMap(vbit);
	 * vbit.put("PRIMA", new BigDecimal(4));
	 * vbit.put("SUMA_ASEGURADA", new BigDecimal(4));
	 * 
	 * inclusion.addCobertura(vbas);
	 * inclusion.addCobertura(vbit);
	 * inclusion.addCobertura(vcat);
	 * 
	 * incremento2.put("NUM_SERVICIO", 4000);
	 * incremento2.put("CVE_SERVICIO", 2208);
	 * incremento2.put("MOTIVO", 631);
	 * incremento2.put("MOVIMIENTO", "INCREMENTO");
	 * incremento2.put("FEC_INI_VIG", new LocalDate(2020, 1, 1));
	 * 
	 * vbas = new CoberturaMap(vbas);
	 * vbas.put("PRIMA", new BigDecimal(700));
	 * vbas.put("PRIMA_BIT", new BigDecimal(7) );
	 * vbas.put("SUMA_ASEGURADA", new BigDecimal(180000));
	 * 
	 * vbit = new CoberturaMap(vbit);
	 * vbit.put("PRIMA", new BigDecimal(5));
	 * vbit.put("SUMA_ASEGURADA", new BigDecimal(5));
	 * 
	 * vcat = new CoberturaMap(vcat);
	 * vcat.put("PRIMA", new BigDecimal(250));
	 * vcat.put("PRIMA_BIT", new BigDecimal(3) );
	 * vcat.put("SUMA_ASEGURADA", new BigDecimal(75000));
	 * 
	 * incremento2.addCobertura(vbas);
	 * incremento2.addCobertura(vbit);
	 * incremento2.addCobertura(vcat);
	 * 
	 * 
	 * cancelacion.put("NUM_SERVICIO", 5000);
	 * cancelacion.put("CVE_SERVICIO", 2214);
	 * cancelacion.put("MOTIVO", 260);
	 * cancelacion.put("MOVIMIENTO", "CANCELACION");
	 * cancelacion.put("FEC_INI_VIG", new LocalDate(2020, 5, 1));
	 * 
	 * vbas = new CoberturaMap(vbas);
	 * vbas.put("PRIMA", new BigDecimal(700));
	 * vbas.put("SUMA_ASEGURADA", new BigDecimal(180000));
	 * 
	 * vbit = new CoberturaMap(vbit);
	 * vbit.put("PRIMA", new BigDecimal(4));
	 * vbit.put("SUMA_ASEGURADA", new BigDecimal(4));
	 * 
	 * vcat = new CoberturaMap(vcat);
	 * vcat.put("PRIMA", new BigDecimal(250));
	 * vcat.put("PRIMA_BIT", new BigDecimal(3) );
	 * vcat.put("SUMA_ASEGURADA", new BigDecimal(75000));
	 * vcat.put("STATUS", "M");
	 * 
	 * cancelacion.addCobertura(vbas);
	 * cancelacion.addCobertura(vbit);
	 * cancelacion.addCobertura(vcat);
	 * 
	 * polizaMap.addServicio(emision);
	 * polizaMap.addServicio(incremento);
	 * polizaMap.addServicio(inclusion);
	 * polizaMap.addServicio(incremento2);
	 * polizaMap.addServicio(cancelacion);
	 * 
	 * 
	 * //simula problemas en las capas
	 * polizaMap.getServicios().get("5000").getCobertura("VBAS").put("EDAD",40);
	 * polizaMap.getServicios().get("3000").getCobertura("VBAS").put("HABITO","");
	 * polizaMap.getServicios().get("3000").getCobertura("VBAS").put("SEXO","");
	 * polizaMap.getServicios().get("3000").getCobertura("VBIT").put("SEXO","2");
	 * polizaMap.getServicios().get("4000").getCobertura("VCAT").put("EDAD", 40);
	 * polizaMap.getServicios().get("4000").getCobertura("VCAT").put("SEXO", "F");
	 * polizaMap.getServicios().get("4000").getCobertura("VBIT").put("SEXO", null);
	 * 
	 * return polizaMap;
	 * 
	 * }
	 */

	public PolizaMap createPoliza() {
		PolizaMap polizaMap = new PolizaMap(new HashMap());

		try (InputStream is = getClass().getResourceAsStream("/poliza_data.ser");

				ObjectInputStream in = new ObjectInputStream(is)) {
			// Deserializar el archivo .ser de vuelta a un mapa
			Map<String, Serializable> data = (Map<String, Serializable>) in.readObject();
			polizaMap.putAll(data);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return polizaMap;
	}
}

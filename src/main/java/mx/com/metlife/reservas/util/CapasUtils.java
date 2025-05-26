package mx.com.metlife.reservas.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.com.metlife.reservas.main.domain.CoberturaMap;
import mx.com.metlife.reservas.main.domain.ServicioMap;

public class CapasUtils {

	public static void ordenaCapas(List<Object> servicios) {
		Collections.sort(servicios, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				ServicioMap s1 = (ServicioMap) o1;
				ServicioMap s2 = (ServicioMap) o2;
				return ((Integer)s2.get("NUM_SERVICIO")).compareTo((Integer)s1.get("NUM_SERVICIO"));
			}
		});
	
	}
	public static void valorAbsolutoPrimas(CoberturaMap c) {
		c.put("SUMA_ASEGURADA", Math.abs(c.cDouble("SUMA_ASEGURADA")));
		c.put("PRIMA", Math.abs(c.cDouble("PRIMA")));
		c.put("MEDICO", Math.abs(c.cDouble("MEDICO")));
		c.put("OCUPACIONAL", Math.abs(c.cDouble("OCUPACIONAL")));
	}


	public static void restaPrimasCobertura(CoberturaMap c1, CoberturaMap c2) {
		BigDecimal prima = c1.cDec("PRIMA").subtract(c2.cDec("PRIMA"));
		BigDecimal medico = c1.cDec("MEDICO").subtract(c2.cDec("MEDICO"));
		BigDecimal ocupacional = c1.cDec("OCUPACIONAL").subtract(c2.cDec("OCUPACIONAL"));

		c1.put("PRIMA", prima);
		c1.put("MEDICO", medico);
		c1.put("OCUPACIONAL", ocupacional);
	}
	
	

}

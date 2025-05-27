package mx.com.metlife.reservas.util;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class FechasUtils {
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat formatCat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static LocalDate parsetoDateYYYYMMDD(int fecha) throws Exception{
		return LocalDate.fromDateFields(sdfYYYYMMDD.parse(String.valueOf(fecha)));
	}
	
	
	public static LocalDate getLocalDate(String cadena) {
		try {
			return LocalDate.fromDateFields(formatCat.parse(cadena));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Boolean isFechaEquals(LocalDate fecha1, LocalDate fecha2){
		
		if(fecha1 == null || fecha2 == null){
			return false;
		}
		
		
		
		return fecha1.isEqual(fecha2);
	}
	
	public static  synchronized Boolean isFechaMayor(LocalDate fecha1, LocalDate fecha2){
		if(fecha1 == null || fecha2 == null){
			return false;
		}
		
		return fecha1.isAfter(fecha2);
	}
	
	
	public static synchronized Boolean isFechaMenor(LocalDate fecha1, LocalDate fecha2){
		if(fecha1 == null || fecha2 == null){
			return false;
		}
		
		return fecha1.isBefore(fecha2);
	}

	
	
	public static  synchronized Boolean isFechaMayorIgual(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isAfter(fecha2) || fecha1.isEqual(fecha2);
	}
	
	
	public static synchronized Boolean isFechaMayorOIgual(LocalDate fechaAComparar, String valorComparacion){
		
		if(fechaAComparar == null){
			return false;
		}
		
		LocalDate fecha2;
		try {
			fecha2 = LocalDate.fromDateFields(sdf.parse(valorComparacion));
			return fechaAComparar.isAfter(fecha2) || fechaAComparar.isEqual(fecha2);
		} catch (ParseException e) {
			return false;
		} 
		
	}
	
	public static  synchronized Boolean isFechaMenor(LocalDate fechaAComparar, String valorComparacion){
		if(fechaAComparar == null){
			return false;
		}
		LocalDate fecha2;
		try {
			fecha2 = LocalDate.fromDateFields(sdf.parse(valorComparacion));
			return fechaAComparar.isBefore(fecha2);
		} catch (ParseException e) {
			return false;
		} 
	}
	
	
	
	public static  synchronized int evaluaFondoAfectar(String fondoAfectar, int numFondo, BigDecimal saldoFondo){
		if(fondoAfectar == null){
			return 0;
		}
		String[] fondos = fondoAfectar.split(",");
		int res = 0;
		switch (numFondo) {
		case 1:
			if(saldoFondo.doubleValue() != 0 || StringUtils.equals(fondos[0], "1R")){
				res = 1;
			}
			break;
		case 2:
			if(StringUtils.equals(fondos[0], "2R")){
				res = 1;
			}
			break;
		default:
			if(saldoFondo.doubleValue() != 0 || StringUtils.equals(fondos[1], "1E")){
				res = 1;
			}
			break;
		}
	
		return res;
	}
	
	
	public static  synchronized boolean checkCobertura(String cve){
		if(cve.equals("VBAS")){
			return true;
		}
		

		return false;
	}
	
	
	
	public static synchronized  String getDia(LocalDate date){
		return StringUtils.leftPad(String.valueOf(date.getDayOfMonth()), 2, '0');
	}
	
	public static  synchronized String getMes(LocalDate date){
		return StringUtils.leftPad(String.valueOf(date.getMonthOfYear()), 2, '0');
	}
	
	public static synchronized  int getAnnioVigencia(LocalDate fechaCalculoReserva, LocalDate fechaInicioVigencia){
		int annios = Years.yearsBetween(fechaInicioVigencia, fechaCalculoReserva).getYears();
		return annios+1;
	}
	
	public static  synchronized int getEdadReal(LocalDate fechaNacAsegurado, LocalDate fechaInicioVigenciaServicio){
		int annios = Years.yearsBetween(fechaInicioVigenciaServicio, fechaNacAsegurado).getYears();		
		return annios;
	}
	
	public static  synchronized int getEdadAlcanzadaFactorX(LocalDate fechaCalculoReserva, LocalDate fechaInicioVigencia){
		int annios = Years.yearsBetween(fechaInicioVigencia, fechaCalculoReserva).getYears();
		return annios;
	}
}

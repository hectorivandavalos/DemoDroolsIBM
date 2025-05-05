package mx.com.metlife.commons.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

/**
 * clase para manejar los formatos
 * @author USERMET01
 *
 */
public class FormatUtil {
	
	
	private static DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	private static DecimalFormat nf = new DecimalFormat("#,###,##0");
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * formatea un importe al formato moneda
	 * @param valor importe
	 * @return cadena formateada
	 */
	public static String formatImporte(Double valor){
		if(valor != null){
			return "$"+df.format(valor.doubleValue());
		}else{
			return "$0.00";
		}
	}
	
	public static String formatNumber(Integer valor){
		if(valor != null){
			return nf.format(valor);
		}else{
			return "0";
		}
	}
	
	/**
	 * formatea la fecha
	 * @param fecha fecha
	 * @return cadena formateada
	 */
	public static String formatFecha(Date fecha){
		if(fecha == null){
			return StringUtils.EMPTY;
		}
		try{
			return dateFormat.format(fecha);
		}catch(Exception e){
			return String.valueOf(fecha);
		}
	}
	
	
	/**
	 * formatea la fecha
	 * @param fecha fecha
	 * @return cadena formateada
	 * @throws ParseException 
	 */
	public static LocalDate getDate(String fecha) throws ParseException{
		return new LocalDate(dateFormat.parse(fecha));
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	/*public static String getFmtReferencia(String referencia){
		
		try{
			String strReferencia = StringUtils.trim(referencia);
			if(strReferencia.length() == 14){
				return StringUtils.substring(strReferencia, 0,4) + AppConstants.REFERENCIA_SEPARADOR + StringUtils.substring(strReferencia, 4,14);
			}else{
				return "0000" + AppConstants.REFERENCIA_SEPARADOR + StringUtils.leftPad(strReferencia, 10, '0');
			}
		}catch(Exception e){
			return referencia;
		}
		
	}*/
	
	/**
	 * 
	 * @param cuenta
	 * @return
	 */
	public static String getFmtCuenta(Long cuenta){
		if(cuenta != null){
			String value = String.valueOf(cuenta);
			String fmt = StringUtils.substring(value,0, 4) + StringUtils.repeat("*", value.length()-8) + StringUtils.substring(value, value.length()-4,value.length());
			return fmt;
		}
		return StringUtils.EMPTY;
	}

}

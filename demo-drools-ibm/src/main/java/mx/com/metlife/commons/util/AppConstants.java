package mx.com.metlife.commons.util;

import java.math.BigDecimal;

/**
 * Constantes de la aplicación
 * @author GCIT
 *
 */
public interface AppConstants {
	
	public static final int CAT_PLANES = 4;
	public static final int CAT_PRODUCTO = 27;
	public static final int CAT_RETIROS = 33;
	public static final int CAT_PLANES_PROD = 35;

	
	public static final BigDecimal CIEN = new BigDecimal(100);
	public static final BigDecimal MIL = new BigDecimal(1000);
	public static final BigDecimal DOS = new BigDecimal(2);
	public static final BigDecimal NEGATIVO = new BigDecimal(-1);
	public static final BigDecimal CINCUENTA = new BigDecimal(50);
	public static final BigDecimal CINCUENTA_N = new BigDecimal(-50);
	
	public static final BigDecimal FACT_MENSUAL = new BigDecimal(1/12d);           //1
	public static final BigDecimal FACT_TRIMESTRAL = new BigDecimal(1/4d);         //4
	public static final BigDecimal FACT_SEMESTRAL = new BigDecimal(1/2d);          //6
	public static final BigDecimal FACT_ANUAL = BigDecimal.ONE;                    //12
	
	public static final String [] ESTATUS_NO_CALCULAR = {"A", "M", "S", "I", "C"};
	
	public static final Integer ROL_ADMIN = 1;
	public static final Integer ROL_CONFIG = 2;
	public static final Integer ROL_OPER = 3;
	public static final Integer ROL_TEC = 4;
	
	public static final Integer ESTATUS_NUEVO = 1;
	public static final Integer ESTATUS_EN_PROCESO = 2;
	public static final Integer ESTATUS_DETENER = 3;
	public static final Integer ESTATUS_EJECUTADO = 4;
	public static final Integer ESTATUS_CANCELADO = 6;
	public static final Integer ESTATUS_CONFIRMAR = 0;
	
	public static final String NUEVO_PROCESO = "N";
	public static final String DETENER_PROCESO = "D";
	public static final String REINICIAR_PROCESO = "R";
	public static final String CANCELAR_PROCESO = "C";
	
	public static final String AJUSTE_SAR_POSITIVO = "POSITIVOS";
	public static final String AJUSTE_SAR_NEGATIVO = "NEGATIVOS";
	
	public static final Integer ESTATUS_AJUSTE_CONFIRMAR = 0;
	public static final Integer ESTATUS_AJUSTE_REALIZADO = 1;
	public static final Integer ESTATUS_AJUSTE_CANCELADO = 2;
	
	public static final int TIPO_PROCESO_REC_RESERVA = 1;
	public static final int TIPO_PROCESO_VIDAS_INC = 2;
	public static final int TIPO_PROCESO_CONTABILIDAD = 3;
	public static final int TIPO_PROCESO_VALIDACIONES = 4;
	public static final int TIPO_PROCESO_SOBRANTES = 5;
	public static final int TIPO_PROCESO_REC_MENSUAL = 6;
	public static final int TIPO_PROCESO_REC_BATCH = 7;
	public static final int TIPO_PROCESO_SERVICIOS = 8;
	public static final int TIPO_PROCESO_CORRECCION_RVA = 11;
	
	public static final int TIPO_PROCESO_REC_2212 = 13;
	
	public static final String ADMIN = "admin";
	
}

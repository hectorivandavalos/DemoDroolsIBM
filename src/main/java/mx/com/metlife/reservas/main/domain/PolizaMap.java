package mx.com.metlife.reservas.main.domain;

import mx.com.metlife.reservas.calc.domain.SaldosReserva;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.*;

public class PolizaMap extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3955920260001992397L;
	
	private List<Pago> pagos = new ArrayList<Pago>();
	
	private List<Retiro> retiros = new ArrayList<Retiro>();
	
	private List<Retiro> reversionRetiro = new ArrayList<Retiro>();
	
	private List<PrimaDeposito> primasDeposito = new ArrayList<PrimaDeposito>();
	
	private List<Map<String, Object>> logCambios;
	
	private List<Map<String, Object>> logDiferenciaPago;
	
	private List<SaldosReserva> saldos = new ArrayList<SaldosReserva>();
	
	private List<StringBuilder> capas;
	
	private LocalDate fechaFinCalculo;
	
	private List<Map<String, Object>> logDiferenciaPrimas;
	
	private List<String> ajustes;
	
	private List<Ajuste335> ajustes335 = new ArrayList<Ajuste335>();
	
	private List<AjusteSaldoPorFondo> ajustesSaldoPorFondo;
	
	private List<CoberturaRetarificada> cobsRetarificadas;
	
	private List<SobranteDetalleMap> listSobranteDetalleSar;
	
	private List<AjusteSaldoPorFondo> ajustesGeneral;
	
	private List<CoiDetalleMap> listCoiDetalleSar;
	
	private List<DetalleRetiroRva> listConceptosSumario;
	
	private List<Ajuste330> ajustes330;
	
	
	private boolean has2212 = false;
	
	public PolizaMap(Map<String, Object> map){
		this.put("SERVICIOS", new LinkedHashMap<String, ServicioMap>());
		this.put("SERVICIOS_RETARIFICADOS", new LinkedHashMap<String, ServicioMap>());
		this.putAll(map);
		this.logCambios = new ArrayList<Map<String, Object>>();
		this.logDiferenciaPago = new ArrayList<Map<String, Object>>();
		this.logDiferenciaPrimas = new ArrayList<Map<String,Object>>();
		this.ajustes = new ArrayList<String>();
		
		this.saldos = new ArrayList<SaldosReserva>();
		
		this.put("CAPAS", new ArrayList<HistorialCapa>());
		this.put("SERVICIOS_RESERVA", new ArrayList<ServicioReserva>());
		
		this.ajustesSaldoPorFondo = new ArrayList<AjusteSaldoPorFondo>();
		
		this.ajustesGeneral = new ArrayList<AjusteSaldoPorFondo>();
	}
	
	@SuppressWarnings("unchecked")
	public List<HistorialCapa> getHistorialCapas(){
		return (List<HistorialCapa>) this.get("CAPAS");
	}
	
	public void resetHistorialCapas(){
		this.put("CAPAS", new ArrayList<HistorialCapa>());
	}
	
	public void addCambio(String campo, Object servicio, String cobertura, Object valorAnterior, Object valorNuevo){
		Map<String, Object> cambio = new HashMap<String, Object>();
		cambio.put("CAMPO", campo);
		cambio.put("SERVICIO", servicio.toString());
		cambio.put("COBERTURA", cobertura);
		cambio.put("VALOR_ANTERIOR", valorAnterior);
		cambio.put("VALOR_NUEVO", valorNuevo);
		this.logCambios.add(cambio);  
		
	}
	
	public void addDiferencia(LocalDate mes, BigDecimal totalPrimas, BigDecimal totalPago){
		Map<String, Object> dif = new HashMap<String, Object>();
		dif.put("MES", mes);
		dif.put("TOTAL_PAGO", totalPago);
		dif.put("TOTAL_PRIMAS", totalPrimas);
		dif.put("DIFERENCIA", totalPago.subtract(totalPrimas));
		this.logDiferenciaPago.add(dif);  
	}
	
	public void addDiferenciaPrimas(LocalDate mes, String poliza, LocalDate fechaPago, BigDecimal primaPagada, BigDecimal primaCobrada, String tipoMov,
									BigDecimal proporcion, Integer numPago, String numServicio, String cveCobertura, BigDecimal prima){
		Map<String, Object> difPrimas = new HashMap<String, Object>();
		difPrimas.put("MES", mes);
		difPrimas.put("POLIZA", poliza);
		difPrimas.put("FECHA_PAGO", fechaPago);
		difPrimas.put("PRIMA_PAGADA", primaPagada);
		difPrimas.put("PRIMA_COBRADA", primaCobrada);
		difPrimas.put("TIPO_MOV", tipoMov);
		difPrimas.put("PROPORCION", proporcion);
		difPrimas.put("NUM_PAGO", numPago);
		difPrimas.put("NUM_SERVICIO", numServicio);
		difPrimas.put("CVE_COBERTURA", cveCobertura);
		difPrimas.put("PRIMA_COB", prima.multiply(proporcion));
		this.logDiferenciaPrimas.add(difPrimas);
	}
	
	
	/**
	 * @return the logCambios
	 */
	public List<Map<String, Object>> getLogCambios() {
		return logCambios;
	}

	/**
	 * @param logCambios the logCambios to set
	 */
	public void setLogCambios(List<Map<String, Object>> logCambios) {
		this.logCambios = logCambios;
	}

	/**
	 * 
	 * @return
	 */
	public String getPoliza() {
		return (String) this.get("NUM_POLIZA");
	}

	public int getPolizaNumerica() {
		return ((BigDecimal) this.get("NMPOLIZA")).intValue();
	}
	
	public String getPolizaAlfaNumerica() {
		return (String) this.get("NMPOLANT");
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, ServicioMap> getServicios() {
		return (Map<String, ServicioMap>) this.get("SERVICIOS");
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, ServicioMap> getServiciosRetarificados() {
		return (Map<String, ServicioMap>) this.get("SERVICIOS_RETARIFICADOS");
	}

	/**
	 * 
	 * @param servicios
	 */
	public void addServicios(List<ServicioMap> servicios) {
		Integer cscServ = 1;
		for(ServicioMap servicio : servicios){
			servicio.put("ID_SERV", cscServ);
			servicio.put("ID_SERV_ANT", cscServ-1);
			servicio.put("SERVICIO_MOTIVO", servicio.get("CVE_SERVICIO") + "-" + servicio.get("MOTIVO"));
			servicio.put("FACTOR_CAMBIO_FP", BigDecimal.ONE );
			this.addServicio(servicio);
			cscServ++;
		}
	}

	/**
	 * 
	 * @param servicio
	 */
	public void addServicio(ServicioMap servicio){
		servicio.put("SERVICIO_MOTIVO", servicio.get("CVE_SERVICIO") + "-" + servicio.get("MOTIVO"));
		servicio.put("#primaOrig", servicio.get("PRIMA_COBRO"));
		servicio.put("#primaExcOrig", servicio.get("PRIMA_EXC"));
		servicio.put("FACTOR_CAMBIO_FP", BigDecimal.ONE );
		this.getServicios().put(servicio.getKey(), servicio);
	}
	
	
	/**
	 * 
	 * @param servicio
	 */
	public void addServicioRetarificado(ServicioMap servicio){
		ServicioMap copy = new ServicioMap(servicio);
		for(Map.Entry<String,CoberturaMap> cob : servicio.getCoberturas().entrySet()){
			CoberturaMap cc = new CoberturaMap();
			cc.putAll(cob.getValue());
			copy.addCobertura(cc);
		}
		this.getServiciosRetarificados().put(copy.getKey(), copy);
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

	/**
	 * @return the pagos
	 */
	public List<Pago> getPagos() {
		return pagos;
	}

	/**
	 * @param pagos the pagos to set
	 */
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}


	/**
	 * @return the retiros
	 */
	public List<Retiro> getRetiros() {
		return retiros;
	}

	/**
	 * @param retiros the retiros to set
	 */
	public void setRetiros(List<Retiro> retiros) {
		if(retiros != null) {
			this.retiros = retiros;	
		}
	}

	/**
	 * @return the reversionRetiro
	 */
	public List<Retiro> getReversionRetiro() {
		return reversionRetiro;
	}

	/**
	 * @param reversionRetiro the reversionRetiro to set
	 */
	public void setReversionRetiro(List<Retiro> reversionRetiro) {
		if(reversionRetiro != null) {
			this.reversionRetiro = reversionRetiro;	
		}
	}

	/**
	 * @return the primasDeposito
	 */
	public List<PrimaDeposito> getPrimasDeposito() {
		return primasDeposito;
	}

	/**
	 * @param primasDeposito the primasDeposito to set
	 */
	public void setPrimasDeposito(List<PrimaDeposito> primasDeposito) {
		if(primasDeposito != null) {
			this.primasDeposito = primasDeposito;
		}
		
	}

	/**
	 * @return the saldos
	 */
	public List<SaldosReserva> getSaldos() {
		return saldos;
	}

	/**
	 * @param saldos the saldos to set
	 */
	public void setSaldos(List<SaldosReserva> saldos) {
		this.saldos = saldos;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void addSaldo(SaldosReserva s){
		this.saldos.add(s);
	}

	public LocalDate getFechaFinCalculo() {
		return fechaFinCalculo;
	}

	public void setFechaFinCalculo(LocalDate fechaFinCalculo) {
		this.fechaFinCalculo = fechaFinCalculo;
	}

	/**
	 * @return the logDiferenciaPago
	 */
	public List<Map<String, Object>> getLogDiferenciaPago() {
		return logDiferenciaPago;
	}

	/**
	 * @return the capas
	 */
	public List<StringBuilder> getCapas() {
		return capas;
	}

	/**
	 * @param capas the capas to set
	 */
	public void setCapas(List<StringBuilder> capas) {
		this.capas = capas;
	}
	
	
	/**
	 * 
	 * @param capa
	 */
	public void addCapas(StringBuilder capa){
		
		if(this.capas == null){
			this.capas = new ArrayList<StringBuilder>(); 
		}
	
		this.capas.add(capa);
	}
	
	
	/**
	 * guarda el estatus de los servicios actuales 
	 * @param 
	 */
	public void saveCapas(String servicioAfectacion, Date fechaOperacion){
		
		HistorialCapa capa = new HistorialCapa();
		capa.setServicio(servicioAfectacion);
		capa.setFechaOperacion(fechaOperacion);
		
	    List<HistorialCapa> capasHistorial = this.getHistorialCapas();
		
	    List<ServicioMap> capasActual = new ArrayList<ServicioMap>();
		
		for(Map.Entry<String, ServicioMap> servEntry : this.getServicios().entrySet() ){
			ServicioMap servicio = servEntry.getValue();
			ServicioMap servicioCopy = new ServicioMap(servicio);
			for(Map.Entry<String,CoberturaMap> cob : servicio.getCoberturas().entrySet()){
				CoberturaMap coberturaCopy = new CoberturaMap();
				coberturaCopy.putAll(cob.getValue());
				servicioCopy.addCobertura(coberturaCopy);
			}
			capasActual.add(servicioCopy);
		}
		
		capa.setCapas(capasActual);
		
		capasHistorial.add(capa);
		
	}
	
	
	/**
	 * @return the logDiferenciaPrimas
	 */
	public List<Map<String, Object>> getLogDiferenciaPrimas() {
		return logDiferenciaPrimas;
	}

	/**
	 * @param logDiferenciaPrimas the logDiferenciaPrimas to set
	 */
	public void setLogDiferenciaPrimas(List<Map<String, Object>> logDiferenciaPrimas) {
		this.logDiferenciaPrimas = logDiferenciaPrimas;
	}

	
	public void addAjuste(String sql){
		this.ajustes.add(sql);
	}

	/**
	 * @return the ajustes
	 */
	public List<String> getAjustes() {
		return ajustes;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicioReserva> getServiciosReserva(){
		return (List<ServicioReserva>) this.get("SERVICIOS_RESERVA");
	}
	
	/**
	 * 
	 * @param servs
	 */
	public void setServiciosReserva(List<ServicioReserva> servs) {
		this.put("SERVICIOS_RESERVA", servs);
	}

	/**
	 * @return the ajustes335
	 */
	public List<Ajuste335> getAjustes335() {
		return ajustes335;
	}

	/**
	 * @param ajustes335 the ajustes335 to set
	 */
	public void setAjustes335(List<Ajuste335> ajustes335) {
		this.ajustes335 = ajustes335;
	}
	
	/**
	 * @return the ajustesSaldoPorFondo
	 */
	public List<AjusteSaldoPorFondo> getAjustesSaldoPorFondo() {
		return ajustesSaldoPorFondo;
	}

	/**
	 * @param ajustesSaldoPorFondo the ajustesSaldoPorFondo to set
	 */
	public void setAjustesSaldoPorFondo(List<AjusteSaldoPorFondo> ajustesSaldoPorFondo) {
		this.ajustesSaldoPorFondo = ajustesSaldoPorFondo;
	}
	
	
	public List<CoberturaRetarificada> getCobsRetarificadas() {
		return cobsRetarificadas;
	}

	public void setCobsRetarificadas(List<CoberturaRetarificada> cobsRetarificadas) {
		this.cobsRetarificadas = cobsRetarificadas;
	}

	public List<SobranteDetalleMap> getListSobranteDetalleSar() {
		return listSobranteDetalleSar;
	}

	public void setListSobranteDetalleSar(List<SobranteDetalleMap> listSobranteDetalleSar) {
		this.listSobranteDetalleSar = listSobranteDetalleSar;
	}
	
	/**
	 * @return the ajustesGeneral
	 */
	public List<AjusteSaldoPorFondo> getAjustesGeneral() {
		return ajustesGeneral;
	}

	/**
	 * @param ajustesGeneral the ajustesGeneral to set
	 */
	public void setAjustesGeneral(List<AjusteSaldoPorFondo> ajustesGeneral) {
		this.ajustesGeneral = ajustesGeneral;
	}
	
	
	public void enable2212() {
		this.has2212 = true;
	}
	
	public boolean has2212() {
		return this.has2212;
	}
	
	/**
	 * @return the listCoiDetalleSar
	 */
	public List<CoiDetalleMap> getListCoiDetalleSar() {
		return listCoiDetalleSar;
	}

	/**
	 * @param listCoiDetalleSar the listCoiDetalleSar to set
	 */
	public void setListCoiDetalleSar(List<CoiDetalleMap> listCoiDetalleSar) {
		this.listCoiDetalleSar = listCoiDetalleSar;
	}

	/**
	 * @return the listConceptosSumario
	 */
	public List<DetalleRetiroRva> getListConceptosSumario() {
		return listConceptosSumario;
	}

	/**
	 * @param listConceptosSumario the listConceptosSumario to set
	 */
	public void setListConceptosSumario(List<DetalleRetiroRva> listConceptosSumario) {
		this.listConceptosSumario = listConceptosSumario;
	}

	/**
	 * @return the ajustes330
	 */
	public List<Ajuste330> getAjustes330() {
		return ajustes330;
	}

	/**
	 * @param ajustes330 the ajustes330 to set
	 */
	public void setAjustes330(List<Ajuste330> ajustes330) {
		this.ajustes330 = ajustes330;
	}
	
	

}

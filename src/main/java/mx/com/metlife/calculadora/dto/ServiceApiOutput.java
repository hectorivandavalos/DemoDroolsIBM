package mx.com.metlife.calculadora.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para representar los datos de un servicio en la API
 */
public class ServiceApiOutput {
    private String servicio;
    private String cve;
    private String mot;
    private String plan;
    private String pft;
    private String fechaIniVig;
    private String fechaOpera;
    private BigDecimal primaCob;
    private BigDecimal primaExc;
    private String descripcionMovimiento;
    private String statusServicio;
    private List<CoverageApiOutput> coberturas = new ArrayList<>();

    /**
     * Constructor por defecto para Jackson
     */
    public ServiceApiOutput() {
        // Constructor vacío requerido para serialización/deserialización
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCve() {
        return cve;
    }

    public void setCve(String cve) {
        this.cve = cve;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPft() {
        return pft;
    }

    public void setPft(String pft) {
        this.pft = pft;
    }

    public String getFechaIniVig() {
        return fechaIniVig;
    }

    public void setFechaIniVig(String fechaIniVig) {
        this.fechaIniVig = fechaIniVig;
    }

    public String getFechaOpera() {
        return fechaOpera;
    }

    public void setFechaOpera(String fechaOpera) {
        this.fechaOpera = fechaOpera;
    }

    public BigDecimal getPrimaCob() {
        return primaCob;
    }

    public void setPrimaCob(BigDecimal primaCob) {
        this.primaCob = primaCob;
    }

    public BigDecimal getPrimaExc() {
        return primaExc;
    }

    public void setPrimaExc(BigDecimal primaExc) {
        this.primaExc = primaExc;
    }

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public String getStatusServicio() {
        return statusServicio;
    }

    public void setStatusServicio(String statusServicio) {
        this.statusServicio = statusServicio;
    }

    public List<CoverageApiOutput> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoverageApiOutput> coberturas) {
        this.coberturas = coberturas;
    }
    
    public void addCobertura(CoverageApiOutput cobertura) {
        this.coberturas.add(cobertura);
    }
}

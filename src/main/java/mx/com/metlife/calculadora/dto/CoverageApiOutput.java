package mx.com.metlife.calculadora.dto;

import java.math.BigDecimal;

/**
 * DTO para representar los datos de una cobertura en la API
 */
public class CoverageApiOutput {
    private String cveCobertura;
    private BigDecimal sumaAsegurada;
    private BigDecimal prima;
    private BigDecimal vbasMedica;
    private BigDecimal ocupacional;
    private BigDecimal primaBit;
    private Integer edad;
    private String sexo;
    private String habito;
    private String statusCobertura;

    public CoverageApiOutput() {
    }

    public String getCveCobertura() {
        return cveCobertura;
    }

    public void setCveCobertura(String cveCobertura) {
        this.cveCobertura = cveCobertura;
    }

    public BigDecimal getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(BigDecimal sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    public BigDecimal getVbasMedica() {
        return vbasMedica;
    }

    public void setVbasMedica(BigDecimal vbasMedica) {
        this.vbasMedica = vbasMedica;
    }

    public BigDecimal getOcupacional() {
        return ocupacional;
    }

    public void setOcupacional(BigDecimal ocupacional) {
        this.ocupacional = ocupacional;
    }

    public BigDecimal getPrimaBit() {
        return primaBit;
    }

    public void setPrimaBit(BigDecimal primaBit) {
        this.primaBit = primaBit;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getHabito() {
        return habito;
    }

    public void setHabito(String habito) {
        this.habito = habito;
    }

    public String getStatusCobertura() {
        return statusCobertura;
    }

    public void setStatusCobertura(String statusCobertura) {
        this.statusCobertura = statusCobertura;
    }
}

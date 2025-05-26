package mx.com.metlife.calculadora.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO para representar la respuesta completa del análisis de póliza
 */
public class PolicyAnalysisResponse {
    private List<ServiceApiOutput> capasIniciales = new ArrayList<>();
    private List<ServiceApiOutput> capasFinales = new ArrayList<>();
    
    /**
     * Constructor por defecto para Jackson
     */
    public PolicyAnalysisResponse() {
        // Constructor vacío requerido para serialización/deserialización
    }

    public List<ServiceApiOutput> getCapasIniciales() {
        return capasIniciales;
    }

    public void setCapasIniciales(List<ServiceApiOutput> capasIniciales) {
        this.capasIniciales = capasIniciales;
    }

    public List<ServiceApiOutput> getCapasFinales() {
        return capasFinales;
    }

    public void setCapasFinales(List<ServiceApiOutput> capasFinales) {
        this.capasFinales = capasFinales;
    }
    
    public void addCapaInicial(ServiceApiOutput servicio) {
        this.capasIniciales.add(servicio);
    }
    
    public void addCapaFinal(ServiceApiOutput servicio) {
        this.capasFinales.add(servicio);
    }
}

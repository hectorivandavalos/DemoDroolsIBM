package mx.com.metlife.calculadora.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.metlife.calculadora.exception.PolizaDataException;
import mx.com.metlife.calculadora.service.PolizaDataReaderService;
import mx.com.metlife.reservas.main.domain.CoberturaMap;
import mx.com.metlife.reservas.main.domain.PolizaMap;
import mx.com.metlife.reservas.main.domain.ServicioMap;

/**
 * Utilidad para convertir datos de pólizas serializados a formato CSV
 */
@Component
public class PolizaDataCsvConverter {
    
    private static final Logger logger = LoggerFactory.getLogger(PolizaDataCsvConverter.class);
    
    private final PolizaDataReaderService polizaDataReaderService;
    
    @Autowired
    public PolizaDataCsvConverter(PolizaDataReaderService polizaDataReaderService) {
        this.polizaDataReaderService = polizaDataReaderService;
    }
    
    /**
     * Convierte los datos serializados a archivos CSV
     * @param outputDirectory Directorio donde se guardarán los archivos CSV generados
     * @return Arreglo con las rutas a los archivos CSV generados
     */
    public String[] convertToCsv(String outputDirectory) {
        logger.info("Iniciando conversión de datos serializados a CSV en directorio: {}", outputDirectory);
        
        try {
            // Cargar datos serializados
            PolizaMap polizaMap = polizaDataReaderService.loadInitialData();
            
            // Crear directorio de salida si no existe
            Path outputPath = Paths.get(outputDirectory);
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
            
            // Generar CSVs
            String serviciosPath = generateServiciosCSV(polizaMap, outputDirectory);
            String coberturasPath = generateCoberturasCSV(polizaMap, outputDirectory);
            String polizaInfoPath = generatePolizaInfoCSV(polizaMap, outputDirectory);
            
            logger.info("Conversión a CSV completada exitosamente");
            return new String[] { serviciosPath, coberturasPath, polizaInfoPath };
            
        } catch (Exception e) {
            logger.error("Error al convertir datos serializados a CSV", e);
            throw new PolizaDataException("Error al generar archivos CSV", e);
        }
    }
    
    /**
     * Genera un archivo CSV con la información de servicios
     */
    private String generateServiciosCSV(PolizaMap polizaMap, String outputDirectory) throws IOException {
        String filePath = outputDirectory + "/servicios.csv";
        logger.info("Generando CSV de servicios: {}", filePath);
        
        try (FileWriter writer = new FileWriter(filePath)) {
            // Cabecera del CSV
            writer.append("SERVICIO,CVE,MOT,PLAN,PFT,FECHA_INI_VIG,FECHA_OPERA,PRIMA_COB,PRIMA_EXC,DESCRIPCION_MOVIMIENTO,STATUS_SERVICIO\n");
            
            // Contenido
            Map<String, ServicioMap> servicios = polizaMap.getServicios();
            for (ServicioMap servicio : servicios.values()) {
                writer.append(servicio.get("NUM_SERVICIO") + ",");
                writer.append(servicio.get("CVE_SERVICIO") + ",");
                writer.append(servicio.get("MOTIVO") + ",");
                writer.append(servicio.get("PLAN") + ",");
                writer.append(servicio.get("DEV_PRIMA") + ",");
                
                // Convertir fechas si están disponibles
                try {
                    writer.append(servicio.cDate("FEC_INI_VIG").toString() + ",");
                } catch (Exception e) {
                    writer.append(",");
                }
                
                try {
                    writer.append(servicio.cDate("FEC_OPERACION").toString() + ",");
                } catch (Exception e) {
                    writer.append(",");
                }
                
                // Numéricas
                try {
                    writer.append(servicio.cDec("PRIMA_COBRO") + ",");
                } catch (Exception e) {
                    writer.append(",");
                }
                
                try {
                    writer.append(servicio.cDec("PRIMA_EXC") + ",");
                } catch (Exception e) {
                    writer.append(",");
                }
                
                writer.append(servicio.get("MOVIMIENTO") + ",");
                writer.append(servicio.get("STATUS") + "\n");
            }
        }
        
        return filePath;
    }
    
    /**
     * Genera un archivo CSV con la información de coberturas
     */
    private String generateCoberturasCSV(PolizaMap polizaMap, String outputDirectory) throws IOException {
        String filePath = outputDirectory + "/coberturas.csv";
        logger.info("Generando CSV de coberturas: {}", filePath);
        
        try (FileWriter writer = new FileWriter(filePath)) {
            // Cabecera del CSV
            writer.append("SERVICIO,CVE_COBERTURA,SUMA_ASEGURADA,PRIMA,VBAS_MEDICA,OCUPACIONAL,PRIMA_BIT,EDAD,SEXO,HABITO,STATUS_COBERTURA\n");
            
            // Contenido
            Map<String, ServicioMap> servicios = polizaMap.getServicios();
            for (ServicioMap servicio : servicios.values()) {
                String numServicio = servicio.get("NUM_SERVICIO").toString();
                Map<String, CoberturaMap> coberturas = servicio.getCoberturas();
                
                for (CoberturaMap cobertura : coberturas.values()) {
                    writer.append(numServicio + ",");
                    writer.append(cobertura.getKey() + ",");
                    
                    // Numéricas
                    try {
                        writer.append(cobertura.cDec("SUMA_ASEGURADA") + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    try {
                        writer.append(cobertura.cDec("PRIMA") + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    try {
                        writer.append(cobertura.cDec("MEDICO") + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    try {
                        writer.append(cobertura.cDec("OCUPACIONAL") + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    try {
                        writer.append(cobertura.cDec("PRIMA_BIT") + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    // Otros datos
                    try {
                        Object edad = cobertura.get("EDAD");
                        if (edad == null) {
                            edad = cobertura.get("NUM_HIJOS");
                        }
                        writer.append(edad + ",");
                    } catch (Exception e) {
                        writer.append(",");
                    }
                    
                    writer.append(cobertura.get("SEXO") + ",");
                    writer.append(cobertura.get("HABITO") + ",");
                    writer.append(cobertura.get("STATUS") + "\n");
                }
            }
        }
        
        return filePath;
    }
    
    /**
     * Genera un archivo CSV con la información general de la póliza
     */
    private String generatePolizaInfoCSV(PolizaMap polizaMap, String outputDirectory) throws IOException {
        String filePath = outputDirectory + "/poliza_info.csv";
        logger.info("Generando CSV de información general de la póliza: {}", filePath);
        
        try (FileWriter writer = new FileWriter(filePath)) {
            // Cabecera del CSV
            writer.append("CLAVE,VALOR\n");
            
            // Información general de la póliza
            writer.append("NUM_POLIZA," + polizaMap.getPoliza() + "\n");
            
            // Agregar campos adicionales que puedan ser relevantes
            for (Map.Entry<String, Object> entry : polizaMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                
                // Excluir objetos complejos
                if (value instanceof String || value instanceof Number || value instanceof Boolean) {
                    writer.append(key + "," + value + "\n");
                }
            }
        }
        
        return filePath;
    }
    
    /**
     * Método principal para ejecutar la conversión desde línea de comandos
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java PolizaDataCsvConverter <directorio_salida>");
            System.exit(1);
        }
        
        String outputDir = args[0];
        
        try {
            // Crear instancia del lector de datos
            PolizaDataReaderService readerService = new PolizaDataReaderService();
            
            // Crear el convertidor
            PolizaDataCsvConverter converter = new PolizaDataCsvConverter(readerService);
            
            // Ejecutar la conversión
            String[] generatedFiles = converter.convertToCsv(outputDir);
            
            System.out.println("Conversión completada. Archivos generados:");
            for (String file : generatedFiles) {
                System.out.println(" - " + file);
            }
            
        } catch (Exception e) {
            System.err.println("Error durante la conversión: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
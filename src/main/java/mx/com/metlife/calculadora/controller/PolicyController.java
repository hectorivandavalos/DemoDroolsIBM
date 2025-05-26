package mx.com.metlife.calculadora.controller;

import java.math.BigDecimal;
// import java.text.SimpleDateFormat; - No es necesario
import java.util.List;
import java.util.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.metlife.calculadora.dto.CoverageApiOutput;
import mx.com.metlife.calculadora.dto.PolicyAnalysisResponse;
import mx.com.metlife.calculadora.dto.ServiceApiOutput;
import mx.com.metlife.calculadora.service.DroolsService;
import mx.com.metlife.calculadora.service.PolizaDataReaderService;
import mx.com.metlife.calculadora.util.PolizaDataCsvConverter;
import mx.com.metlife.reservas.main.domain.CoberturaMap;
import mx.com.metlife.reservas.main.domain.PolizaMap;
import mx.com.metlife.reservas.main.domain.ServicioMap;

import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Controlador REST para el análisis de pólizas usando Drools
 */
@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    // Constantes para evitar duplicación
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String NUM_HIJOS_KEY = "NUM_HIJOS";

    @Autowired
    private PolizaDataReaderService polizaDataReaderService;

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private PolizaDataCsvConverter polizaDataCsvConverter;

    /**
     * Endpoint para analizar una póliza y aplicar las reglas de Drools
     * 
     * @return Respuesta con las capas iniciales y finales
     */
    @GetMapping("/analyze")
    public ResponseEntity<PolicyAnalysisResponse> analyzePolicy() {
        System.out.println("Iniciando análisis de póliza");

        // Cargamos los datos iniciales de la póliza
        PolizaMap initialPoliza = polizaDataReaderService.loadInitialData();

        // Mapeamos el estado inicial a la respuesta
        PolicyAnalysisResponse response = new PolicyAnalysisResponse();
        mapPolizaToResponse(initialPoliza, response.getCapasIniciales());

        // Ejecutamos las reglas de Drools en una copia de los datos
        PolizaMap processedPoliza = droolsService.executeRules(initialPoliza);

        // Mapeamos el estado final después de aplicar las reglas
        mapPolizaToResponse(processedPoliza, response.getCapasFinales());

        System.out.println("Análisis de póliza completado exitosamente");
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para exportar los datos de póliza en formato CSV
     * 
     * @return Archivo ZIP conteniendo los CSV generados
     */
    @GetMapping("/export-csv")
    public ResponseEntity<Resource> exportPolizaDataToCsv() {
        try {
            System.out.println("Iniciando exportación de datos de póliza a formato CSV");

            // Crear directorio temporal para los archivos CSV
            String tempDir = System.getProperty("java.io.tmpdir") + "/poliza_data_" + System.currentTimeMillis();
            Files.createDirectories(Paths.get(tempDir));

            // Convertir datos a CSV
            String[] csvFiles = polizaDataCsvConverter.convertToCsv(tempDir);

            // Crear archivo ZIP con los CSV generados
            String zipFilePath = tempDir + "/poliza_data_export.zip";
            try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
                for (String csvFile : csvFiles) {
                    File fileToZip = new File(csvFile);
                    zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
                    Files.copy(fileToZip.toPath(), zipOut);
                    zipOut.closeEntry();
                }
            }

            // Preparar respuesta HTTP con el archivo ZIP
            Resource resource = new UrlResource(Paths.get(zipFilePath).toUri());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=poliza_data_export.zip");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            System.out.println("Exportación CSV completada exitosamente");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            System.out.println("Error al exportar datos de póliza a CSV: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para exportar datos individuales a formato CSV
     * 
     * @param type Tipo de datos a exportar (servicios, coberturas, poliza_info)
     * @return Archivo CSV específico solicitado
     */
    @GetMapping("/export-csv/{type}")
    public ResponseEntity<Resource> exportSpecificCsv(@RequestParam String type) {
        try {
            System.out.println("Iniciando exportación de datos específicos '" + type + "' a formato CSV");

            // Validar tipo solicitado
            if (!type.equals("servicios") && !type.equals("coberturas") && !type.equals("poliza_info")) {
                return ResponseEntity.badRequest().build();
            }

            // Crear directorio temporal para los archivos CSV
            String tempDir = System.getProperty("java.io.tmpdir") + "/poliza_data_" + System.currentTimeMillis();
            Files.createDirectories(Paths.get(tempDir));

            // Convertir datos a CSV
            String[] csvFiles = polizaDataCsvConverter.convertToCsv(tempDir);

            // Encontrar el archivo solicitado
            String targetFile = null;
            for (String csvFile : csvFiles) {
                if (csvFile.endsWith(type + ".csv")) {
                    targetFile = csvFile;
                    break;
                }
            }

            if (targetFile == null) {
                return ResponseEntity.notFound().build();
            }

            // Preparar respuesta HTTP con el archivo CSV
            Resource resource = new UrlResource(Paths.get(targetFile).toUri());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + type + ".csv");
            headers.setContentType(MediaType.parseMediaType("text/csv"));

            System.out.println("Exportación CSV de '" + type + "' completada exitosamente");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            System.out.println("Error al exportar datos específicos a CSV: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para obtener información resumida sobre los datos de póliza
     * 
     * @return Resumen de la estructura de datos
     */
    @GetMapping("/data-summary")
    public ResponseEntity<Map<String, Object>> getPolizaDataSummary() {
        try {
            System.out.println("Generando resumen de datos de póliza");

            PolizaMap poliza = polizaDataReaderService.loadInitialData();
            Map<String, Object> summary = new HashMap<>();

            // Información básica de la póliza
            summary.put("numPoliza", poliza.getPoliza());

            // Conteo de servicios
            Map<String, ServicioMap> servicios = poliza.getServicios();
            summary.put("totalServicios", servicios.size());

            // Conteo de coberturas
            int totalCoberturas = 0;
            for (ServicioMap servicio : servicios.values()) {
                totalCoberturas += servicio.getCoberturas().size();
            }
            summary.put("totalCoberturas", totalCoberturas);

            // Listo para ser presentado al cliente
            summary.put("csvDisponibles", new String[] { "servicios.csv", "coberturas.csv", "poliza_info.csv" });

            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            System.out.println("Error al generar resumen de datos de póliza: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Mapea los datos de la póliza a la estructura de servicios para la respuesta
     * API
     */
    private void mapPolizaToResponse(PolizaMap poliza, List<ServiceApiOutput> services) {
        for (Map.Entry<String, ServicioMap> entry : poliza.getServicios().entrySet()) {
            ServicioMap servicioMap = entry.getValue();
            ServiceApiOutput service = new ServiceApiOutput();

            // Mapeamos los datos del servicio
            service.setServicio(
                    servicioMap.get("NUM_SERVICIO") != null ? String.valueOf(servicioMap.get("NUM_SERVICIO")) : null);
            service.setCve(
                    servicioMap.get("CVE_SERVICIO") != null ? String.valueOf(servicioMap.get("CVE_SERVICIO")) : null);
            service.setMot(servicioMap.get("MOTIVO") != null ? String.valueOf(servicioMap.get("MOTIVO")) : null);
            service.setPlan(servicioMap.get("PLAN") != null ? String.valueOf(servicioMap.get("PLAN")) : null);
            service.setPft(servicioMap.get("DEV_PRIMA") != null ? String.valueOf(servicioMap.get("DEV_PRIMA")) : null);

            // Formateo de fechas desde el archivo serializado
            try {
                // Intentamos diferentes nombres de campo para las fechas
                Object fechaIniVigObj = servicioMap.get("FEC_INI_VIG");
                if (fechaIniVigObj == null) {
                    fechaIniVigObj = servicioMap.get("FECHA_INI_VIG");
                }

                if (fechaIniVigObj != null) {
                    if (fechaIniVigObj instanceof LocalDate) {
                        service.setFechaIniVig(((LocalDate) fechaIniVigObj).toString(DATE_FORMAT));
                    } else if (fechaIniVigObj instanceof java.util.Date) {
                        service.setFechaIniVig(
                                new java.text.SimpleDateFormat(DATE_FORMAT).format((java.util.Date) fechaIniVigObj));
                    } else {
                        service.setFechaIniVig(fechaIniVigObj.toString());
                    }
                }

                // Fecha de operación
                Object fechaOperaObj = servicioMap.get("FEC_OPERACION");
                if (fechaOperaObj == null) {
                    fechaOperaObj = servicioMap.get("FECHA_OPERA");
                }

                if (fechaOperaObj != null) {
                    if (fechaOperaObj instanceof LocalDate) {
                        service.setFechaOpera(((LocalDate) fechaOperaObj).toString(DATE_FORMAT));
                    } else if (fechaOperaObj instanceof java.util.Date) {
                        service.setFechaOpera(
                                new java.text.SimpleDateFormat(DATE_FORMAT).format((java.util.Date) fechaOperaObj));
                    } else {
                        service.setFechaOpera(fechaOperaObj.toString());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al procesar fechas para el servicio " + service.getServicio() + ": " + e.getMessage());
            }

            // Valores numéricos
            if (servicioMap.get("PRIMA_COBRO") instanceof BigDecimal) {
                service.setPrimaCob((BigDecimal) servicioMap.get("PRIMA_COBRO"));
            }

            if (servicioMap.get("PRIMA_EXC") instanceof BigDecimal) {
                service.setPrimaExc((BigDecimal) servicioMap.get("PRIMA_EXC"));
            }

            // Otros campos
            service.setDescripcionMovimiento(
                    servicioMap.get("MOVIMIENTO") != null ? String.valueOf(servicioMap.get("MOVIMIENTO")) : null);
            service.setStatusServicio(
                    servicioMap.get("STATUS") != null ? String.valueOf(servicioMap.get("STATUS")) : null);

            // Mapeamos las coberturas
            for (Map.Entry<String, CoberturaMap> cobEntry : servicioMap.getCoberturas().entrySet()) {
                CoberturaMap coberturaMap = cobEntry.getValue();
                CoverageApiOutput coverage = mapCobertura(coberturaMap);
                service.addCobertura(coverage);
            }

            services.add(service);
        }
    }

    /**
     * Mapea los datos de una cobertura al DTO correspondiente
     */
    private CoverageApiOutput mapCobertura(CoberturaMap coberturaMap) {
        CoverageApiOutput coverage = new CoverageApiOutput();

        coverage.setCveCobertura(coberturaMap.getKey());

        if (coberturaMap.get("SUMA_ASEGURADA") instanceof BigDecimal) {
            coverage.setSumaAsegurada((BigDecimal) coberturaMap.get("SUMA_ASEGURADA"));
        }

        if (coberturaMap.get("PRIMA") instanceof BigDecimal) {
            coverage.setPrima((BigDecimal) coberturaMap.get("PRIMA"));
        }

        if (coberturaMap.get("MEDICO") instanceof BigDecimal) {
            coverage.setVbasMedica((BigDecimal) coberturaMap.get("MEDICO"));
        }

        if (coberturaMap.get("OCUPACIONAL") instanceof BigDecimal) {
            coverage.setOcupacional((BigDecimal) coberturaMap.get("OCUPACIONAL"));
        }

        if (coberturaMap.get("PRIMA_BIT") instanceof BigDecimal) {
            coverage.setPrimaBit((BigDecimal) coberturaMap.get("PRIMA_BIT"));
        }

        if (coberturaMap.get("EDAD") != null) {
            Integer edad = null;
            if (coberturaMap.get("EDAD") instanceof Integer) {
                edad = (Integer) coberturaMap.get("EDAD");
            } else if (coberturaMap.get("EDAD") instanceof BigDecimal) {
                edad = ((BigDecimal) coberturaMap.get("EDAD")).intValue();
            } else if (coberturaMap.get("EDAD") instanceof String) {
                try {
                    edad = Integer.parseInt((String) coberturaMap.get("EDAD"));
                } catch (NumberFormatException e) {
                    // Si no se puede convertir, usamos el valor de NUM_HIJOS si existe
                    if (coberturaMap.get(NUM_HIJOS_KEY) instanceof Integer) {
                        edad = (Integer) coberturaMap.get(NUM_HIJOS_KEY);
                    }
                }
            }
            coverage.setEdad(edad);
        } else if (coberturaMap.get(NUM_HIJOS_KEY) instanceof Integer) {
            coverage.setEdad((Integer) coberturaMap.get(NUM_HIJOS_KEY));
        }

        coverage.setSexo(coberturaMap.get("SEXO") != null ? String.valueOf(coberturaMap.get("SEXO")) : null);
        coverage.setHabito(coberturaMap.get("HABITO") != null ? String.valueOf(coberturaMap.get("HABITO")) : null);
        coverage.setStatusCobertura(
                coberturaMap.get("STATUS") != null ? String.valueOf(coberturaMap.get("STATUS")) : null);

        return coverage;
    }
}

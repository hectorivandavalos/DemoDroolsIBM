package mx.com.metlife.calculadora.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.metlife.calculadora.exception.PolizaDataException;

import mx.com.metlife.reservas.main.domain.PolizaMap;

/**
 * Servicio para leer datos de póliza desde archivos serializados
 */
@Service
public class PolizaDataReaderService {
    
    private static final Logger logger = LoggerFactory.getLogger(PolizaDataReaderService.class);
    
    /**
     * Carga los datos iniciales de la póliza desde el archivo serializado
     * 
     * @return Una instancia de PolizaMap con los datos cargados
     */
    public PolizaMap loadInitialData() {
        logger.info("Cargando datos iniciales de póliza desde archivo serializado");
        PolizaMap polizaMap = new PolizaMap(new HashMap<>());
        
        try (InputStream is = getClass().getResourceAsStream("/poliza_data.ser");
            ObjectInputStream in = new CustomObjectInputStream(is)) {
            // Deserializar el archivo .ser de vuelta a un mapa
            @SuppressWarnings("unchecked")
            Map<String, Serializable> data = (Map<String, Serializable>) in.readObject();
            polizaMap.putAll(data);
            logger.info("Datos de póliza cargados exitosamente");
        } catch (IOException e) {
            logger.error("Error de E/S al cargar datos iniciales de póliza", e);
            throw new PolizaDataException("Error de E/S al cargar datos de póliza desde archivo serializable", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error de compatibilidad de clase al cargar datos de póliza", e);
            throw new PolizaDataException("Error de compatibilidad de clases al deserializar objeto desde archivo", e);
        }
        
        return polizaMap;
    }
    
    /**
     * ObjectInputStream personalizado para manejar posibles cambios en nombres de paquetes
     * debido a la deserialización de objetos serializados con versiones anteriores.
     */
    static class CustomObjectInputStream extends ObjectInputStream {
        public CustomObjectInputStream(InputStream is) throws IOException {
            super(is);
        }
        
        @Override
        protected java.lang.Class<?> resolveClass(java.io.ObjectStreamClass desc) 
                throws IOException, ClassNotFoundException {
            try {
                return super.resolveClass(desc);
            } catch (ClassNotFoundException e) {
                // Si la clase no se encuentra, intenta buscarla con el nombre simple
                // Esto ayuda con posibles cambios de paquete en la migración
                String name = desc.getName();
                try {
                    return Class.forName(name);
                } catch (ClassNotFoundException ex) {
                    logger.warn("No se pudo resolver la clase: {}", name);
                    throw e;
                }
            }
        }
    }
}

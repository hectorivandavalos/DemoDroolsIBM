package mx.com.metlife.calculadora.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Clase para manejar los logs de reglas de Drools
 * Esta clase se inyecta como global en las sesiones Drools
 */
@Component
public class DroolsLogger implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DroolsLogger.class);

    /**
     * Log a nivel DEBUG
     */
    public void debug(String message) {
        logger.debug("[DROOLS] " + message);
        // También imprimimos a consola para facilitar el seguimiento durante el
        // desarrollo
        System.out.println("[DROOLS-DEBUG] " + message);
    }

    /**
     * Log a nivel INFO
     */
    public void info(String message) {
        logger.info("[DROOLS] " + message);
        System.out.println("[DROOLS-INFO] " + message);
    }

    /**
     * Log específico para orquestador (compatible con log.orquestadorDebug antiguo)
     */
    public void orquestadorDebug(String message) {
        logger.debug("[DROOLS-ORQUESTADOR] " + message);
        System.out.println("[DROOLS-ORQUESTADOR] " + message);
    }

    /**
     * Log para advertencias
     */
    public void warn(String message) {
        logger.warn("[DROOLS] " + message);
        System.out.println("[DROOLS-WARN] " + message);
    }

    /**
     * Log para errores
     */
    public void error(String message) {
        logger.error("[DROOLS] " + message);
        System.err.println("[DROOLS-ERROR] " + message);
    }

    /**
     * Log para errores con excepción
     */
    public void error(String message, Throwable t) {
        logger.error("[DROOLS] " + message, t);
        System.err.println("[DROOLS-ERROR] " + message + ": " + t.getMessage());
    }
}

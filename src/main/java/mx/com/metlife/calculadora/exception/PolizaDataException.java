package mx.com.metlife.calculadora.exception;

/**
 * Excepción personalizada para errores relacionados con los datos de la póliza
 */
public class PolizaDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con mensaje de error
     * 
     * @param message Mensaje descriptivo del error
     */
    public PolizaDataException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje de error y causa original
     * 
     * @param message Mensaje descriptivo del error
     * @param cause Excepción original que causó el problema
     */
    public PolizaDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

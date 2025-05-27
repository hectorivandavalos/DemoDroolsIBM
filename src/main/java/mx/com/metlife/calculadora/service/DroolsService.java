package mx.com.metlife.calculadora.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.metlife.reservas.main.domain.PolizaMap;

@Service
public class DroolsService {

    @Autowired
    private KieContainer kieContainer;

    /**
     * Ejecuta las reglas de 'analisis-capas.drl' sobre una copia profunda de la
     * póliza
     * 
     * @param poliza La póliza original, que no se modificará
     * @return Una nueva instancia de poliza con los cálculos aplicados
     */
    public PolizaMap executeRules(PolizaMap poliza) {
        // Hacemos una copia profunda de la póliza para no modificar la original
        PolizaMap polizaClone = deepClone(poliza);

        // Creamos una nueva sesión de Drools
        System.out.println("Iniciando ejecución de reglas para análisis de capas");
        KieSession kieSession = kieContainer.newKieSession();

        try {
            // Insertamos la póliza clonada y sus servicios/coberturas en la sesión
            putSessionPolizaData(polizaClone, kieSession);

            // Establecemos el foco de la agenda en el grupo 'orquestador-analisis-capas'
            kieSession.getAgenda().getAgendaGroup("orquestador-analisis-capas").setFocus();
            int rulesExecuted = kieSession.fireAllRules();
            System.out.println("Se ejecutaron " + rulesExecuted + " reglas en el grupo orquestador-analisis-capas");

            // De manera similar, luego aplicamos el grupo 'ajuste-valores'
            kieSession.getAgenda().getAgendaGroup("ajuste-valores").setFocus();
            rulesExecuted = kieSession.fireAllRules();
            System.out.println("Se ejecutaron " + rulesExecuted + " reglas en el grupo ajuste-valores");

            return polizaClone;
        } finally {
            // Siempre liberamos recursos para evitar leaks de memoria
            kieSession.dispose();
        }
    }

    /**
     * Inserta los datos de la póliza y sus servicios/coberturas en la sesión de
     * Drools
     */
    private void putSessionPolizaData(PolizaMap poliza, KieSession session) {
        session.insert(poliza);

        poliza.getServicios().values().forEach(servicio -> {
            session.insert(servicio);
            servicio.getCoberturas().values().forEach(cobertura -> {
                session.insert(cobertura);
            });
        });
    }

    /**
     * Realiza una copia profunda (deep clone) del objeto PolizaMap
     * utilizando serialización, lo que maneja correctamente los gráficos de objetos
     */
    @SuppressWarnings("unchecked")
    private <T> T deepClone(T object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new MyCustomObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error realizando deep clone del objeto: " + e.getMessage());
            throw new RuntimeException("Error al clonar objeto para procesamiento", e);
        }
    }

    /**
     * Clase interna para manejar la deserialización
     */
    private static class MyCustomObjectInputStream extends ObjectInputStream {
        
        public MyCustomObjectInputStream(ByteArrayInputStream bis) throws IOException {
            super(bis);
        }

        @Override
        protected Class<?> resolveClass(java.io.ObjectStreamClass desc)
                throws IOException, ClassNotFoundException {
            try {
                return super.resolveClass(desc);
            } catch (ClassNotFoundException e) {
                // Si la clase no se encuentra, intenta buscarla con el nombre simple
                return Class.forName(desc.getName());
            }
        }
    }
}

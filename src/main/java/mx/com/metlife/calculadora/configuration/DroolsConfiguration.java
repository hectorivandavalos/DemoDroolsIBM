package mx.com.metlife.calculadora.configuration;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DroolsConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DroolsConfiguration.class);
	private final KieServices kieServices = KieServices.get();

	@Bean
	public KieContainer kieContainer() {
		logger.info("Configurando contenedor KIE para Drools IBM BAMOE 9.1.1-ibm-0003");
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource("rules/analisis-capas.drl"));
		// Solo cargamos el archivo analisis-capas.drl según requerimiento
		// kieFileSystem.write(ResourceFactory.newClassPathResource("coi.drl"));
		// kieFileSystem.write(ResourceFactory.newClassPathResource("reserva.drl"));
		KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}
}

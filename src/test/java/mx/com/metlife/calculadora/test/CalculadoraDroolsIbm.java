package mx.com.metlife.calculadora.test;



import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.metlife.calculadora.BootDroolsApplication;
import mx.com.metlife.calculadora.service.CalculadoraEngine;
import mx.com.metlife.calculadora.service.PolizaService;
import mx.com.metlife.reservas.main.domain.PolizaMap;


@SpringBootTest(classes = BootDroolsApplication.class)
public class CalculadoraDroolsIbm {

    @Autowired
    private PolizaService service;
    
    @Autowired
    private CalculadoraEngine engine;

    @Test
    public void testDrools() {
         
    	PolizaMap polizaTest = service.createPoliza();
    	 
     	engine.execute(polizaTest, new LocalDate(2023,11,1));
         
     }
}

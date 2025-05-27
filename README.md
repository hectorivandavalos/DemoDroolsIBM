# Migración de Drools 6 a Drools 9.1.1_IBM_0003

Este proyecto demuestra la migración exitosa de un motor de reglas Drools 6 a la versión 9.1.1_IBM_0003.

## Cambios Realizados

1. **Actualización de dependencias**: Se actualizaron las dependencias de Drools a la versión 9.1.1 en el archivo `pom.xml`.
2. **Corrección de declaración de paquetes**: Se corrigió la declaración del paquete en el archivo de reglas `analisis-capas.drl` de `package resources.rules` a `package rules` para alinearlo con la estructura de directorios.
3. **Creación de servicio Drools**: Se implementó un servicio dedicado para gestionar la ejecución de reglas de Drools.
4. **Implementación de API REST**: Se desarrolló una API REST para exponer los resultados del análisis.
5. **Estructura de respuesta JSON**: Se crearon DTOs para estructurar la respuesta JSON con las capas iniciales y finales.
6. **Manejo adecuado de excepciones**: Se implementaron excepciones personalizadas.
7. **Clonación profunda de objetos**: Se implementó una solución para clonar profundamente los objetos y evitar modificar los datos originales.

## Requisitos Previos

- Java 17
- Maven 3.8+
- Puerto 9090 disponible para el servidor web

## Ejecución

Para ejecutar el proyecto:

```bash
mvn spring-boot:run
```

## Acceso a la API

Una vez iniciado, puedes acceder a la API REST en:

- `http://localhost:8090/api/policy/analyze` - Realiza el análisis de la póliza

## Estructura de la Respuesta

La respuesta JSON tiene la siguiente estructura:

```json
{
  "capasIniciales": [
    {
      "servicio": "7368",
      "cve": "null",
      "mot": "0",
      "plan": "9999",
      "pft": null,
      "fechaIniVig": "1992-10-01",
      "fechaOpera": "1992-10-01",
      "primaCob": 97.74,
      "primaExc": 0.00,
      "coberturas": [
        {
          "cveCobertura": "VBAS",
          "sumaAsegurada": 120000.00,
          "prima": 77.30,
          "vbasMedica": 0.00,
          "ocupacional": 0.00,
          "statusCobertura": "V"
        }
      ]
    }
  ],
  "capasFinales": [
    // Datos procesados después de aplicar las reglas
  ]
}
```

## Notas Importantes

- Este proyecto solo ejecuta las 7 reglas del archivo `analisis-capas.drl`.
- Los cálculos son idénticos a los valores de referencia en la imagen proporcionada.
- Los facts provienen directamente del archivo serializable sin modificaciones.

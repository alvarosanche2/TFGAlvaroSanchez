# Trabajo de Fin de Grado - Álvaro Sánchez Pérez

Este repositorio contiene el trabajo de fin de grado, donde se aborda el aprendizaje y aplicación de los fundamentos de computación distribuida con Spark. El objetivo es ejecutar diferentes consultas con distinto grado de complejidad, visualizando y analizando los resultados de manera gráfica. Además se realizara el despliegue de las consultas en cluster AWS. Para el proyecto se trabajará con datos meteorológicos obtenidos de la página oficial de la AEMET.

## Actividades Desarrolladas

- Obtención de datos meteorológicos a través de la página oficial de la AEMET.
- Desarrollo de consultas básicas con Spark en Scala.
- Creación y configuración de un cluster de computación distribuida en Amazon Web Services para su uso con Spark.
- Desarrollo de queries complejas para ilustrar buenas prácticas en el uso eficiente del cluster y aprovechamiento de las técnicas de optimización de Spark.
- Visualización de los resultados de las consultas mediante librerías del ecosistema de Scala y Python.
- Análisis de los resultados obtenidos para obtener conclusiones.

## Enlaces 

A continuación se encuentran enlaces a los recursos utilizados en el proyecto que pueden resultar de interés:

* [Memoria](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/Memoria.pdf)
* [Programa de descarga de datos diarios](https://github.com/alvarosanche2/TFGAlvaroSanchez/tree/main/DescargaDatosPorDias)
* [Programa de descarga de datos mensuales](https://github.com/alvarosanche2/TFGAlvaroSanchez/tree/main/DescargaDatosPorMeses)
* [Programa de procesamiento de datos](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/ConsultasMemoria.ipynb)
* [Visualización de la consulta de olas de calor](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/RepresentacionOlasCalor.ipynb)
* [Programa a desplegar en AWS](https://github.com/alvarosanche2/TFGAlvaroSanchez/tree/main/awsHeatWaves)

## Descarga de los datos

En caso de querer hacer uso de los datos que se emplearon para el proyecto, puedes descargarlos en formato Parquet desde el contenedor S3 de AWS siguiendo estos pasos:

1. Primero de todo se deberá disponer de AWS Cli para poder ejecutar comandos de AWS. En caso de no disponer de este, puedes obtener más información sobre cómo descargarlo en esta [página](https://docs.aws.amazon.com/es_es/cli/latest/userguide/getting-started-install.html)

2. Ejecutar el siguiente comando para realizar la descarga de los datos:

    ```
    aws s3 sync url_del_contenedor nombre_carpeta_destino
    ```

    En nuestro caso, el comando se vería así:

    ```
    aws s3 sync s3://tfg-alvaro-sanchez data
    ```


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

A continuación se encuentran enlaces a los recursos que pueden resultar de interés:

* [Memoria](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/Memoria.pdf)
* [Presentación](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/Presentaci%C3%B3n.pdf)
* Consultas y visualización de resultados:
    * [Precipitación por mes y año en estación meteorológica elegida (Vitigudino - Salamanca)](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/Precipitaci%C3%B3n%20por%20mes%20y%20a%C3%B1o%20en%20estaci%C3%B3n%20meteorol%C3%B3gica%20elegida%20(Vitigudino%20-%20Salamanca).ipynb)
    * [Variación de la temperatura máxima mensual](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/Variaci%C3%B3n%20de%20la%20temperatura%20m%C3%A1xima%20mensual%20(2010%20-%202022).ipynb)
    * [Relación entre la temperatura media anual y la precipitación anual](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/Relaci%C3%B3n%20entre%20la%20temperatura%20media%20anual%20y%20la%20precipitaci%C3%B3n%20anual.ipynb)
    * [Año de la temperatura máxima promedio cada mes](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/A%C3%B1o%20de%20la%20temperatura%20m%C3%A1xima%20promedio%20cada%20mes%20(2010%20-%202022).ipynb)
    * [Olas de calor](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/Olas%20de%20calor.ipynb)
        * [Visualización de los resultados en mapa](https://github.com/alvarosanche2/TFGAlvaroSanchez/blob/main/notebooks/Olas%20de%20calor%20-%20Representaci%C3%B3n%20en%20mapa%20.ipynb)
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


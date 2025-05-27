# Informacion del Proyecto
Proyecto java maven con motor de reconocimiento óptico de caracteres(tesseract), con datos entrenados para las imagenes entregadas, entregando resultados en consola con formato de subtitulos de `AegisSub` `.ass`
* Texto de Imagen a capturar
  ![alt text](src/main/resources/images/0_08_03_274__0_08_06_109_0056900000012800072001280.jpeg)
* Ejemplo de resultado (Terminal):
```
   Dialogue: 0,0:08:03.274,0:08:06.109,Default,,0,0,0,,El análisis de la cámara de seguridad localizada cerca de la escena del crimen ha..
```

# Descripcion del problema
Quería extraer subtítulos codificados de películas o series y convertirlos en un archivo `.ass` para poder seleccionar el subtítulo.

* Necesitaba lo siguiente:
  * Necesitaba algo que pudiera obtener capturas de pantalla de cada subtítulo de un video `.mp4`, así como la información de tiempo para dicho subtítulo.
    * Usé VideoSubFinder https://sourceforge.net/projects/videosubfinder/
      * Es un programa escrito por Simeon Kosnitsky. Toma capturas de pantalla de los subtítulos y las guarda con un nombre que consiste en el momento en que el subtítulo aparece por primera vez y el momento en que desaparece.
  * Necesitaba limpiar algunas imagenes que salieran con ruido
    * De momento uso `Paint` para casos puntuales.
  * Necesitaba algo con OCR(tesseract-ocr) para interpretar los textos de las capturas de pantalla.
    * Construi un proyecto simple en java utilizando el motor de reconocimiento óptico de caracteres de propiedad de Google de codigo abierto. 
    * Utilice el archivos de datos entrenados como `spa.traineddata` desde:
      * https://github.com/tesseract-ocr/tessdata
      * https://tesseract-ocr.github.io/tessdoc/Data-Files.html
  * Necesitaba generar el archivo `.ass` con la informacion lista.
    * Posiblemente se agrega en el mismo proyecto java. (De momento despliega el formato con subtitulos en el terminal)
  * Necesitaba un programa para modificar el subtitulo mal puesto
    * Use AegisSub: https://aegisub.uptodown.com/windows


# Posibles problemas
* https://stackoverflow.com/questions/18419504/java-tesseract-error-in-linux-unable-to-load-library-tesseract-libtesseract


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String[] args) {

        List<String> listFiles = getListFiles("src/main/resources/", "images", "jpeg");
        StringBuilder textFinal = new StringBuilder();
        final String LANGUAGE_TRAINED_DATA="spa";
        final String PATH_TESS_DATA="src/main/resources/tessdata/";
        final String PATH_IMG="src/main/resources/images/";

        //Iteracion de cada imagen encontrada.
        listFiles.stream().forEach(file -> {
            Tesseract tesseract = new Tesseract();
            try {
                //Lenguaje de archivo de datos entrenados a seleccionar
                tesseract.setLanguage(LANGUAGE_TRAINED_DATA);

                // Ubicacion de archivos de datos entrenados
                tesseract.setDatapath(PATH_TESS_DATA);

                // Ubicacion de imagenes
                String text = tesseract.doOCR(new File(PATH_IMG + file));

                //======================================Impresion
                //Logica de formato a retornar
                textFinal.append(returnFormatAegisSub(file,text)).append("\n");

            } catch (TesseractException e) {
//                e.printStackTrace();
            }
        });
        System.out.println(textFinal);
        //TODO Pendiente: Agregar metodo que crea archivo '.ass' con sus propiedades y el contenido generado de las imagenes.
    }

    /**
     * Retorna el formato completo que necesita el archivo '.ass' para ser reconocido en el programa AegisSub
     * Ejm: Dialogue: 0,0:08:03.274,0:08:06.109,Default,,0,0,0,,Ejemplo de texto
     * @param file= Se espera el formato: 0_08_03_274__0_08_06_109_0056900000012800072001280.jpeg
     * @param textProcess = se espera el texto ya extraido de la imagen.
     * @return
     */
    public static String returnFormatAegisSub(String file, String textProcess){

        String newNameFile = file.substring(0,24).replace("__",",");
        String start = returnFormatTimeAegis(newNameFile.substring(0,11));
        String end = returnFormatTimeAegis(newNameFile.substring(12));
        return "Dialogue: 0,"+start+","+end+",Default,,0,0,0,,"+
                textProcess.replace("\n", " "); // Los saltos de linea los quita
    }

    /**
     * Retorna el formato de tiempo con el formato requerido para los archivos de subtitulos '.ass'
     * Ejm: 0:08:03.274
     * @param text = se espera el formato: 0_08_03_274__0_08_06_109
     * @return retorna formato 0,0:08:03.274,0:08:06.109
     */
    public static String returnFormatTimeAegis(String text){
        StringBuilder buffer = new StringBuilder();
        String hh = text.substring(0,1);
        String mm = text.substring(2,4);
        String ss = text.substring(5,7);
        String mss = text.substring(8,11);
        buffer
                .append(hh)
                .append(":")
                .append(mm)
                .append(":")
                .append(ss)
                .append(".")
                .append(mss);
        return buffer.toString();
    }

    /**
     * Listar archivos de directorios
     * NOTAS:
     * - https://es.stackoverflow.com/questions/169031/listar-archivos-de-un-directorio-en-java
     *
     * @return Retorna nombres de archivos con su extension
     */
    public static List<String> getListFiles(String basePath, String path, String extension) {
        Path directory = Paths.get(basePath + path);
        List<String> listFiles = new ArrayList<>();

        //listar archivos
        if (Files.exists(directory, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(directory, LinkOption.NOFOLLOW_LINKS)) {
            try {
                DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(directory, String.format("*.%s", extension));
                ArrayList<Path> files = new ArrayList<>();
                newDirectoryStream.forEach(files::add);
                files.forEach(file -> listFiles.add(file.getFileName().toString()));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return listFiles;
    }
}

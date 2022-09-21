package infrastructure.bussines;

import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.bussines.pojo.Serie;
import infrastructure.bussines.pojo.SerieRequest;
import infrastructure.config.SystemPropertiesConfig;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class InitFilesImpl implements InitFiles{

    private SystemPropertiesConfig propertiesConfig = SystemPropertiesConfig.getInstance();

    @Override
    public SerieRequest getDataSearch() {
        return convertJsonToPojo(propertiesConfig.getPathBase()+propertiesConfig.getProperty("path.source.json"), SerieRequest.class);
    }

    @Override
    public List<String> getListFiles() {
        return getListFiles(propertiesConfig.getPathBase(), propertiesConfig.getProperty("path.source.files"), propertiesConfig.getProperty("config.extension"));
    }

    @Override
    public List<String> getPreviewRename(List<String> listOriginalNameFiles, List<Serie> listDataSearch) {
        return getListNewNameFiles(listOriginalNameFiles, listDataSearch);
    }

    @Override
    public void renameFiles(List<String> listOriginalNameFiles, List<String> listPreviewNewNameFiles) {
        renameListToFiles(propertiesConfig.getPathBase(), propertiesConfig.getProperty("path.source.files"),listOriginalNameFiles,listPreviewNewNameFiles);
    }

    /**
     * Renombrar lista archivos por nuevos nombres
     * @param path
     * @param listOriginalNameFiles
     * @param listPreviewNewNameFiles
     */
    public static void renameListToFiles(String basePath, String path, List<String> listOriginalNameFiles, List<String> listPreviewNewNameFiles){

        //Emparejar nombres originales con los modificados. (List to Map)
        Map<String, String> mapSeriesRename = listOriginalNameFiles.stream()
                .collect(Collectors.toMap(
                        clave -> clave,//transformamos el nombre original en la clave del map
                        valor -> listPreviewNewNameFiles.stream()//Obtencion de nombre modificado que sera el valor por el nombre original
                                .filter(s -> s.contains(valor))
                                .collect(Collectors.toList()).toString()
                        )
                );

        //Renombrado de cada archivos
        TreeMap<String, String> mapSeriesRenameSorted = new TreeMap<>(mapSeriesRename);//para ordenar map
        mapSeriesRenameSorted.forEach((clave, valor) -> {
            //quitar elementos puntulales
            valor = valor.substring(1, valor.length()-1);

            //Preview Renombrar archivo
            Path sourceFilePath = Paths.get(basePath +path+clave);
            Path targetFilePath = Paths.get(basePath +path+valor);
            System.out.println("Renombrando:"+sourceFilePath+"--->"+targetFilePath);

            //Renombrar archivo
            try {
                Files.move(sourceFilePath, targetFilePath);
            } catch (FileAlreadyExistsException ex) {
                System.out.println("Target file already exists");
            } catch (IOException ex) {
                System.out.format("I/O error: %s%n", ex);
            }

        });
    }


    /**
     * Inicio de ejecucion para renombrar series y ovas
     *
     * @param listOriginalNameFiles
     * @param serieFindList
     */
    public List<String> getListNewNameFiles(List<String> listOriginalNameFiles, List<Serie> serieFindList) {
        List<String> newName = new ArrayList<>();
        //obtener listado de archivos a renombrar
        listOriginalNameFiles.forEach(nameFile -> {//=>Iteracion por cada archivo listado

            //obtener listado de Series a buscar
            serieFindList.forEach(objSerie -> {//=>Iteracion por cada serie declarada

                //valida si existe la serie en los archivos listados.
                if (nameFile.contains(objSerie.getNameFindFile())) {

                    //Nuevo nombre de archivo
                    String newNameText = newNameFile(
                            objSerie.getNumberEpisodes(),
                            objSerie.getNumberSeason(),
                            nameFile,
                            objSerie.isAbsoluteOrder(),
                            objSerie.getNumEpiAbsoluteOrderStart());
                    if (!newNameText.equalsIgnoreCase("")){
                        newName.add(newNameText);
                    }
                }
            });
        });

        return newName;
    }

    /**
     * => Renombra Archivo por nuevo nombre con el formato Ejm:'S01E1 - namefile.xxx'
     * - Recorre por cantidad de episodios(declarados)
     * - Valida si el episodio encontrado posee el mismo numero de la iteracion
     *
     * @param numEpisode
     * @param numSeason
     * @param nameFile
     * @return Retorna nuevo nombre de archivo
     */
    public String newNameFile(long numEpisode, long numSeason, String nameFile, boolean absoluteOrder, long absoluteOrderStart) {
        String newName = "";
        for (int i = 1; i <= numEpisode; i++) {//cantidad de veces que debe iterar por numero episodios de una temporada

            //formato a buscar
            String formatNumber = "";
            if (absoluteOrder){
                formatNumber = propertiesConfig.getProperty("format.identify.episodes").replace("XXXX", String.format("%02d", absoluteOrderStart));
                absoluteOrderStart++;
            }else {
                formatNumber = propertiesConfig.getProperty("format.identify.episodes").replace("XXXX", String.format("%02d", i));
            }

            //Valida si el episodio en nombre de archivo posee el mismo numero de la iteracion
            if (nameFile.contains(formatNumber)) {
                final String TV_DB_SEASON = propertiesConfig.getProperty("format.seasons")+ String.format("%02d", numSeason);
                final String TV_DB_EPISODE = propertiesConfig.getProperty("format.episode") + String.format("%02d", i);

                //Preview de nuevo nombre de archivo
                newName = TV_DB_SEASON + TV_DB_EPISODE + " - " + nameFile;
                break; // si lo encuentra sale de la iteracion.
            }
        }
        return newName;
    }

    /**
     * Listar archivos de directorios
     * NOTAS:
     * - https://es.stackoverflow.com/questions/169031/listar-archivos-de-un-directorio-en-java
     *
     * @return Retorna nombres de archivos con su extension
     */
    public List<String> getListFiles(String basePath, String path, String extension) {
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

    /**
     * Conversion de json string a objeto pojo
     * @param jsonText
     * @param tClass
     * @return
     * @param <T>
     */
    public <T> T convertJsonToPojo(String jsonText , Class<T> tClass){
        JSONObject jsonObj = null;
        T tClass1 = null;
        try {
            JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            jsonObj = (JSONObject) jsonParser.parse(getReadFile(jsonText));
        } catch (net.minidev.json.parser.ParseException e) {
            e.printStackTrace();
        }

        //convertJsonToPojo
        tClass1 = new ObjectMapper().convertValue(jsonObj, tClass);

        return tClass1;
    }

    /**
     * Lee un archivo de forma simple o tradicional
     * @param file
     * @return
     */
    public String getReadFile(String file){
        StringBuilder jSonString = new StringBuilder();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jSonString.append(data);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return jSonString.toString();
    }

}

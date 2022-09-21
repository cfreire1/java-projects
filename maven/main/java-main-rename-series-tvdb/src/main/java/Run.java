import infrastructure.bussines.InitFiles;
import infrastructure.bussines.InitFilesImpl;
import infrastructure.config.SystemPropertiesConfig;
import infrastructure.bussines.pojo.SerieRequest;

import java.util.*;

public class Run {

    public static void main(String[] args) {

        System.out.println("INICIANDO...");
        SystemPropertiesConfig.setEnvironment("DEV");
        SystemPropertiesConfig.getInstance();
        InitFiles initFiles = new InitFilesImpl();

        //-------------------------Obtener datos para buscar
        SerieRequest serieRequest = initFiles.getDataSearch();

        //-------------------------Operaciones
        List<String> listOriginalNameFiles = initFiles.getListFiles();
        List<String> listPreviewNewNameFiles = initFiles.getPreviewRename(listOriginalNameFiles,serieRequest.getSerie());
        //-------------------------Impresion
        System.out.println("Listando: "+listOriginalNameFiles.size());
        listOriginalNameFiles.forEach(System.out::println);
        System.out.println("");
        System.out.println("Listando: "+listPreviewNewNameFiles.size());
        listPreviewNewNameFiles.forEach(System.out::println);
        //-------------------------confirmacion antes de modificar
        System.out.println("");
        System.out.println ("Indicar: [SI]: para renombrar archivos , [NO]: para cancelar operacion");
        Scanner entradaEscaner = new Scanner (System.in);
        String entradaTeclado = entradaEscaner.nextLine ();
        if (entradaTeclado.equalsIgnoreCase("SI")){
            System.out.println("Renombrando archivos");
            initFiles.renameFiles(listOriginalNameFiles,listPreviewNewNameFiles);
        }else {
            System.exit(0);
        }
        //runCreateFolder
        //runLocateSeriesInFolder


    }
}

package application.generator;

import net.sf.jasperreports.engine.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ReportJasperUtilImpl implements ReportJasperUtil {

    static final String PATH_FILE_READ = "src/main/resources/jasper/";
    static final String PATH_FILE_WRITE = "src/main/resources/generatePdf/";

    @Override
    public JasperPrint compileReportBaseWithData(String reportBase, Map<String, Object> parameter) {
        JasperPrint jasperPrint = null;
        try {
            //compilar Reporte base
            JasperReport compileJasperFile = JasperCompileManager.compileReport(PATH_FILE_READ + reportBase);//compilar reporte base
            jasperPrint = JasperFillManager.fillReport(compileJasperFile, parameter, new JREmptyDataSource());//print
        } catch (JRException e) {
            System.out.println(e);
        }
        return jasperPrint;
    }

    @Override
    public void compileSubReport(String subReport, String parameterSubReport, Map<String, Object> parameters) {
        try {
            //compilar sub Reporte
            JasperReport compileSubReport = JasperCompileManager.compileReport(PATH_FILE_READ + subReport);//compilar sub reporte
            parameters.put(parameterSubReport, compileSubReport);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    @Override
    public FileOutputStream generatePdfFromReport(String nameFileGenerated, JasperPrint jasperPrint) {
        FileOutputStream pathSave = null;
        try {
            //export reporte a a PDF
            byte[] fileExportPdfFinal = JasperExportManager.exportReportToPdf(jasperPrint);

            //generar file
            pathSave = new FileOutputStream(PATH_FILE_WRITE + nameFileGenerated);
            pathSave.write(fileExportPdfFinal);
            pathSave.close();
        } catch (IOException | JRException e) {
            System.out.println(e);
        }
        return pathSave;
    }
}

package application.generator;

import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileOutputStream;
import java.util.Map;

public interface ReportJasperUtil {
    JasperPrint compileReportBaseWithData(String reportBase, Map<String, Object> parameter);

    void compileSubReport(String subReport, String parameterSubReport, Map<String, Object> parameters);
    FileOutputStream generatePdfFromReport(String nameFileGenerated, JasperPrint jasperPrint);
}

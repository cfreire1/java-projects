package application;

import application.generator.ReportJasperUtil;
import application.generator.ReportJasperUtilImpl;
import application.model.Person;
import application.model.Product;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.*;

public class ExampleImpl implements Example {

    private Person getDataDummyProduct(){
        //------------------------DATOS DUMMY
        Product product = new Product();
        product.setName("Objeto1");
        product.setPrice(1000);
        product.setIva(1.5);

        Product product2 = new Product();
        product2.setName("Objeto2");
        product2.setPrice(3000);
        product2.setIva(1.5);

        Person person = new Person();
        person.setCode(1);
        person.setName("Pepito");
        person.setRut(1);
        person.setDv("9");
        person.setBirthDate(new Date());
        person.setGender(true);
        person.setProductList(Arrays.asList(product,product2));

        return person;
    }

    @Override
    public void generateExampleJasperToPdfDynamic() {
        //------------------------DATOS DUMMY
        Person person = getDataDummyProduct();

        //------------------------init-CODE
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("person", person);
        dataMap.put("imgcherry", "src/main/resources/jasper/cherry.jpg");

        FastDateFormat formato = FastDateFormat.getInstance("dd/MM/yyyy");
        dataMap.put("personBirthDate",formato.format(person.getBirthDate()));
        dataMap.put("productList",person.getProductList());

        //------------------------process
        ReportJasperUtil reportJasperUtil = new ReportJasperUtilImpl();
        reportJasperUtil.compileSubReport("ProductList.jrxml","viewSubReport",dataMap);
        JasperPrint jasperPrint = reportJasperUtil.compileReportBaseWithData("FormExample.jrxml",dataMap);
        reportJasperUtil.generatePdfFromReport("FormExample.pdf",jasperPrint);
    }
}

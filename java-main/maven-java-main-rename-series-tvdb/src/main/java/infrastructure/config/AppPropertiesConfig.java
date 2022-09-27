package infrastructure.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesConfig {

    private static AppPropertiesConfig instanceProperties;
    private static Properties properties;
    private static InputStream inputStream;

    private AppPropertiesConfig() {
    }

    public static AppPropertiesConfig getInstance(){
        if (instanceProperties == null){
            try {
                properties = new Properties();
                inputStream = AppPropertiesConfig.class.getClassLoader().getResourceAsStream("application.properties");
                properties.load(inputStream);
                inputStream.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            instanceProperties = new AppPropertiesConfig();
        }else {
            System.out.println("No se puede crear objeto Properties");
        }
        return instanceProperties;
    }

    public String getProperty(String clave){
        return properties.getProperty(clave);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static String getPropertySystemLocal(String PATH_RESOURCES, String clave, String propertiesText){
        Properties properties = null;
        try {
            properties = new Properties();
            InputStream inputStream = new FileInputStream(PATH_RESOURCES+"config/"+propertiesText);
            properties.load(inputStream);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return properties.getProperty(clave);
    }

}

package infrastructure.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SystemPropertiesConfig {

    private static SystemPropertiesConfig instanceProperties;
    private static Properties properties;
    private static InputStream inputStream;
    private static String PATH_RESOURCES;

    private SystemPropertiesConfig() {
    }

    public static SystemPropertiesConfig getInstance(){
        if (instanceProperties == null){
            try {
                properties = new Properties();
                inputStream = new FileInputStream(PATH_RESOURCES+ "config/system-local.properties");
//                inputStream = AppPropertiesConfig.class.getClassLoader().getResourceAsStream("config/system-local.properties");
                properties.load(inputStream);
                inputStream.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            instanceProperties = new SystemPropertiesConfig();
        }else {
            System.out.println("Ya existe una instacia de Properties");
        }
        return instanceProperties;
    }

    public String getProperty(String clave){
        return properties.getProperty(clave);
    }

    public static String getPathBase() {
        return PATH_RESOURCES;
    }

    public static void setPathResources(String pathResources) {
        PATH_RESOURCES = pathResources;
    }

    /**
     * Seleciona el path cuando ya esta compilado en jar
     * - Path para dev en IDE
     * - Path para cuando este compilado JAR, obtiene el path del sistema en donde se ejecute
     *
     * @return
     */
    public static SystemPropertiesConfig setEnvironment(String env){
        if (env.equalsIgnoreCase("DEV")){setPathResources("src/main/resources/");}
        if (env.equalsIgnoreCase("PROD")){setPathResources(System.getProperty("user.dir")+"/");}

        return instanceProperties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}

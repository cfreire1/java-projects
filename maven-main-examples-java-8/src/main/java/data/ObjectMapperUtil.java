package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.pojo.Product;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * https://www.baeldung.com/jackson-linkedhashmap-cannot-be-cast
 */
public class ObjectMapperUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public  <T> T getJsonObj(String file, TypeReference<List<Product>> tClass){
        T obj = null;
        try {
            obj = (T) objectMapper.readValue(new ClassPathResource(file).getFile(),tClass);
        }catch (IOException e){
            log().getLogger(e);
        }
        return obj;
    }


    public LoggerConfig log(){
        return LoggerConfig.getInstance().setClass(ProductDataRepositoryImpl.class);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}

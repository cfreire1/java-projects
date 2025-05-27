package domain.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.dto.ObjetoJsonRequest;
import domain.dto.ObjetoJsonResponse;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class JsonUtil {


    /**
     * Obtiene los request desde un archivo properties.
     *
     * @param valor valor dentro del properties.
     * @return El valor rescatado del properties.
     */
    public String obtenerStringProperties(String valor){

        String retorno = "";
        try {
            String basepath = "src/main/resources/";
            String propertiesArchive = "mokjson.properties";
            InputStream is=new FileInputStream(basepath+propertiesArchive);
            Properties properties = new Properties();
            properties.load(is);
            retorno = properties.getProperty(valor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    /**
     * Convierte un String con formato Json a un Map de tipo JSONObject
     *
     * @param jSonString Cadena de texto con un formato en json.
     * @return Retorna un objeto de tipo map(JSONObject).
     */
    public JSONObject convertStringJsonToMapJson(String jSonString){
        JSONObject jsonObj = null;
        try {
            JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            jsonObj = (JSONObject) jsonParser.parse(jSonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    /**
     *  Convierte un objeto Domain a un objeto de tipo Map.
     *
     * @param domainObj recibe un objeto domain con atributos.
     * @return Devuelve un map del Domain requerido.
     */
    public Map convertDomainToMap(Object domainObj){

        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(domainObj,Map.class);

        return map;
    }


    /**
     * Convierte el Objeto Map en una cadena String.
     *
     * @param map objeto map.
     * @return String Retorna unformacion den cadena.
     */
    public String convertMapToString(Map map){
        return  new JSONObject().toJSONString(map);
    }

    /*---------------------------------------------------------------------------------*/

    /**
     * Devuelve una instancia con datos cargados de ObjetoJsonRequest, a partir de un String Json.
     *
     * @param jSonRequest Cadena de texto con un formato en json.
     * @return ObjetoJsonRequest Retorna el objeto o domain, con los datos.
     */
    public ObjetoJsonRequest convertStringJsonToInstanceObjetoJsonRequest(String jSonRequest){
        //ObjectMapper().convertValue -> convierte un Map(ObjetoJsonRequest) a un Objeto o Domain ObjetoJsonRequest
        return new ObjectMapper().convertValue(this.convertStringJsonToMapJson(jSonRequest),ObjetoJsonRequest.class);
    }

    /**
     * Devuelve una instancia con datos cargados de ObjetoJsonResponse, a partir de un String Json.
     *
     * @param jSonResponse recibe un string ObjetoJsonResponse.
     * @return ObjetoJsonResponse Retorna el objeto o domain, con los datos.
     */
    public ObjetoJsonResponse convertMapToInstanceObjetoJsonResponse(String jSonResponse){
        //ObjectMapper().convertValue -> convierte un Map(ObjetoJsonResponse) a un Objeto o Domain ObjetoJsonResponse
        return new ObjectMapper().convertValue(this.convertStringJsonToMapJson(jSonResponse),ObjetoJsonResponse.class);
    }

}

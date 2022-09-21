import domain.dto.ObjetoJsonRequest;
import domain.dto.ObjetoJsonResponse;
import domain.mapper.JsonUtil;

import java.util.Map;

public class Ejecutar {

    private static JsonUtil jsonUtil = new JsonUtil();

    public static void main(String[] args) {

        //Instancia con datos - json Request Json a Objeto Domain
        String entradaJsonString = jsonUtil.obtenerStringProperties("objeto.request");
        ObjetoJsonRequest objetoJsonRequest = jsonUtil.convertStringJsonToInstanceObjetoJsonRequest(entradaJsonString);

        //Ejecucion en flujo (Opcional)
        ObjetoJsonResponse objetoJsonResponse  = ejecucionFlujoEjemplo(objetoJsonRequest);

        //Instancia con datos - json Response (Objeto Domain a Json)
        Map mapResponse = jsonUtil.convertDomainToMap(objetoJsonResponse);
        String salidaStringJson = jsonUtil.convertMapToString(mapResponse);

    }


    public static ObjetoJsonResponse ejecucionFlujoEjemplo(ObjetoJsonRequest objetoJsonRequest){

        //Emulando alguna respuesta:
        //Json a Objeto Domain
        String entradaJsonString = jsonUtil.obtenerStringProperties("objeto.response");
        ObjetoJsonResponse objetoJsonResponse = jsonUtil.convertMapToInstanceObjetoJsonResponse(entradaJsonString);

        return objetoJsonResponse;
    }


}

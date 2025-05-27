package com.example.msbackendusers.infrastructure.repository;

import com.example.msbackendusers.domain.model.Phone;
import com.example.msbackendusers.domain.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRestServicesImpl implements UserRestServices {

    @Value("${data-access-layer-bd}")
    private String URL_DAL;

    private HttpHeaders addHeaderRestJson(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private HttpEntity addRequestHttpEntity(Object valor){
        return new HttpEntity(valor, addHeaderRestJson());
    }

    @Override
    public Users saveUser(Users users) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Users> response = restTemplate.postForEntity(URL_DAL+"/save-user", addRequestHttpEntity(users), Users.class);
        return response.getBody();
    }

    @Override
    public boolean existEmail(String email) {
        Map<String,String> datarq = new HashMap();
        datarq.put("email",email);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(URL_DAL+"/exist-email", addRequestHttpEntity(datarq), Boolean.class);
        return response.getBody();
    }

    @Override
    public Phone savePhone(Phone phoneEntity) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Phone> response = restTemplate.postForEntity(URL_DAL+"/save-phone", addRequestHttpEntity(phoneEntity), Phone.class);
        return response.getBody();
    }

    @Override
    public boolean existPhone(long nphone) {
        Map datarq = new HashMap();
        datarq.put("number",nphone);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(URL_DAL+"/exist-phone", addRequestHttpEntity(datarq), Boolean.class);
        return response.getBody();
    }
}

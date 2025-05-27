package com.example.gradlespringbootrestcaptchav2.service;

import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTORequest;
import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTOResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaServicesImpl implements ReCaptchaServices{


    @Value("${recaptcha.secret-key}")
    private String CAPTCHA_SECRET_KEY;

    @Value("${recaptcha.url}")
    private String CAPTCHA_URL;

    @Override
    public ReCaptchaDTOResponse validateTokenCaptchaGoogle(ReCaptchaDTORequest reCaptchaDTORequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap dataRequest = new LinkedMultiValueMap();
        dataRequest.add("secret",CAPTCHA_SECRET_KEY);
        dataRequest.add("response",reCaptchaDTORequest.getToken());
        HttpEntity request = new HttpEntity (dataRequest , httpHeaders);


        ResponseEntity<ReCaptchaDTOResponse> response = restTemplate.postForEntity(CAPTCHA_URL,request,ReCaptchaDTOResponse.class);
        return response.getBody();
    }
}

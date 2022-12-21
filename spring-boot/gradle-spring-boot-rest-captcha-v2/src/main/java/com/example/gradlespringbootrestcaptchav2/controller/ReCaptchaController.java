package com.example.gradlespringbootrestcaptchav2.controller;

import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTOResponse;
import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTORequest;
import com.example.gradlespringbootrestcaptchav2.service.ReCaptchaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest-captcha/")
@CrossOrigin("http://localhost:3000/")
public class ReCaptchaController {

    @Autowired
    private ReCaptchaServices reCaptchaServices;

    @PostMapping("validate")
    public ReCaptchaDTOResponse validateCaptcha(@RequestBody ReCaptchaDTORequest reCaptchaDTORequest){

        //validar a google el token-site
        //ir a servicio
        ReCaptchaDTOResponse reCaptchaDTOrp = reCaptchaServices.validateTokenCaptchaGoogle(reCaptchaDTORequest);
        System.out.println(reCaptchaDTOrp);
        return reCaptchaDTOrp;
    }
}

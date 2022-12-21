package com.example.gradlespringbootrestcaptchav2.service;

import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTOResponse;
import com.example.gradlespringbootrestcaptchav2.dto.ReCaptchaDTORequest;

public interface ReCaptchaServices {

    public ReCaptchaDTOResponse validateTokenCaptchaGoogle(ReCaptchaDTORequest reCaptchaDTORequest);
}

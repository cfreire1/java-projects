package com.example.gradlespringbootrestjwtgenerator.presentation.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class JwtgeneratorController {

    @GetMapping("/generate")
    public ResponseEntity<Map> generateTokenJwt(@RequestBody Map body) throws Exception {

        //generateToken
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        final long JWT_TOKEN_VALIDITY = 1000 * 60; //1 min

        if (!body.containsKey("payload") && !body.containsKey("header") ){
            throw new Exception("No existe propiedades payload o header ");
        }

        String jwt = Jwts.builder()
                .setClaims((Map<String, Object>) body.get("payload"))
                .setHeader((Map<String, Object>) body.get("header"))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key).compact();

        Map<String, String> result = new HashMap<>();
        result.put("token",jwt);


        return ResponseEntity.ok(result);
    }
}

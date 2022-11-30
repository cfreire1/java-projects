package com.example.javaspringbootrestusers.infrastructure.jwt;

import com.example.javaspringbootrestusers.infrastructure.controller.dto.AuthToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class JwtAuthController {

    public static final long JWT_TOKEN_VALIDITY = 1000 * 60; //agregar :1 min
    @Value("${auth.secret.key}")
    private String SECRET_KEY;

    @Value("${auth.claim}")
    private String claimName;

    @PostMapping("/token")
    public ResponseEntity<AuthToken> getToken (){
        return new ResponseEntity<>(AuthToken.builder().tokenJwt(generateToken()).build(), HttpStatus.OK);
    }

    /**
     * Genera token
     * @return
     */
    public String generateToken(){
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN");
        return Jwts.builder()
                .claim(claimName,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date())//Fecha fue creado
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))//Fecha expiracion
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)//Firmar el metodo en HS256 y clave
                .compact();
    }
}

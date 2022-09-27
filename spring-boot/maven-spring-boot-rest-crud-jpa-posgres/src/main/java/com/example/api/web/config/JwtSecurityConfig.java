package com.example.api.web.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtSecurityConfig {

    /**
     * agregar :1 min
     */
    public static final long JWT_TOKEN_VALIDITY = 1000 * 60;
    @Value("${jwt.secret}")
    private String API_KEY;

    /**
     * Genera token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())//usuario
                .setIssuedAt(new Date())//Fecha fue creado
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))//Fecha expiracion
                .signWith(SignatureAlgorithm.HS256,API_KEY)//Firmar el metodo en HS256 y clave
                .compact();
    }

    /**
     * Validar Token
     * - Validar que el usuario que viene en la peticion sea el mismo que en el token
     * - Y que no halla expirado el token
     * @param String token
     * @param String username
     * @return
     */
    public boolean validateToken(String token, String  username){
        return username.equalsIgnoreCase(this.extractUsername(token)) && !this.isTokenIsExpired(token) ;
    }

    /**
     * Se obtiene el 'Subject' del objeto 'Claims' por que es donde esta el usuario(username) de la peticion.
     * @param token
     * @return
     */
    public String extractUsername(String token){
        return this.getClaims(token).getSubject();
    }

    /**
     * Valida si el token ya expiro
     * - Preguntaremos si esta antes(before) de la fecha actual.
     * - SI: true
     * - NO: false
     * @param token
     * @return
     */
    public boolean isTokenIsExpired(String token){
        //Preguntaremos si esta antes(before) de la fecha actual.
        return this.getClaims(token)
                .getExpiration().before(new Date());
    }

    /**
     * Obtiene el token y devuelve el cuerpo del token en objeto(Claims)
     * @param token
     * @return
     */
    private Claims getClaims(String token){
        //1. al parser se le agrega la llave y luego valida que la fima sea correcta
        return Jwts.parser().setSigningKey(API_KEY)

                //2. Va obtener el cuerpo del token separado por cada uno de sus objetos
                .parseClaimsJws(token).getBody();
    }

}

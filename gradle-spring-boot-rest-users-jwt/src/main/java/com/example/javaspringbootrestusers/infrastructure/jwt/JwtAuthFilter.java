package com.example.javaspringbootrestusers.infrastructure.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Este filtro se ejecutara cada vez que se ejecute una peticion (OncePerRequestFilter)
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${auth.secret.key}")
    private String SECRET_KEY;

    @Value("${auth.prefix}")
    private String prefix;

    @Value("${auth.auth-header}")
    private String KEY_HEADER_AUTHORIZATION;

    @Value("${auth.claim}")
    private String claimName;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (existJWTToken(request)) {
                String token = extractJWTToken(request);
                if (validateToken(token)) {

                    //Para autenticarnos dentro del flujo de Spring
                    List<String> authorities = (List) this.getClaims(token).get(claimName);
                    UsernamePasswordAuthenticationToken authToken
                            = new UsernamePasswordAuthenticationToken(
                            getClaims(token).getSubject(),
                            null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            Map<String, String> detalle = new HashMap<>();
            detalle.put("mensaje", "Error del JWT ingresado");

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(detalle));
        }
    }

    /**
     * Extrae el contenido del token
     *
     * @param request
     * @return
     */
    private String extractJWTToken(HttpServletRequest request) {
        return request.getHeader(KEY_HEADER_AUTHORIZATION).replace(prefix, "");
    }

    /**
     * Verifica si existe el Token o el Bearer en la peticion(header)
     *
     * @param request
     * @param response
     * @return
     */
    private boolean existJWTToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(KEY_HEADER_AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(prefix)) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene el token y devuelve el cuerpo del token en objeto(Claims)
     *
     * @param token
     * @return
     */
    private Claims getClaims(String token) {

        return Jwts.parser().setSigningKey(SECRET_KEY) //1. al parser se le agrega la llave y luego valida que la fima sea correcta
                .parseClaimsJws(token).getBody();//2. Va obtener el cuerpo del token separado por cada uno de sus objetos
    }

    /**
     * Validar Token
     * @param String token
     * @return
     */
    public boolean validateToken(String token) {

        Claims claims = this.getClaims(token);
//        String extractUsername = claims.getSubject();//Se obtiene el 'Subject' del objeto 'Claims' por que es donde esta el usuario(username) de la peticion.
        boolean isTokenIsExpired = claims.getExpiration().before(new Date());//Preguntaremos si esta antes(before) de la fecha actual.

        return !isTokenIsExpired;
    }

}

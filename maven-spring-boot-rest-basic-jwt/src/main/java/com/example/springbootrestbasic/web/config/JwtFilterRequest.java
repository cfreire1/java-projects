package com.example.springbootrestbasic.web.config;

import com.example.springbootrestbasic.domain.dto.ApiResponseError;
import com.example.springbootrestbasic.domain.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
 * Filtro de peticiones
 * Este filtro se ejecutara cada vez que se ejecute una peticion (OncePerRequestFilter)
 */
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    public static final String KEY_HEADER_AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    @Autowired
    private JwtSecurityConfig jwtSecurityConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Verificara desde el header de la aplicacaion
     * - Si ese token es correcto
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (this.existJWTToken(request)) {
                String tokenRq = this.extractJWTToken(request);
                String username = jwtSecurityConfig.extractUsername(tokenRq);
                /**
                 * SecurityContextHolder.getContext().getAuthentication()
                 * - Verifica en el contexto que no exista ninguna autentificacion para este usuario
                 */
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    //verifica si el jwt es correcto
                    if (jwtSecurityConfig.validateToken(tokenRq, username)) {

                        //Verifica si el usuario existe en el sistema BD
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        /**
                         * Levantar la session para el usuairo
                         * - userDetails,//datos del usuario
                         * - null,// null por que no mandaremos credenciales particulares
                         * - userDetails.getAuthorities()// se envian todos loa roles que tiene el usuario
                         */
                        UsernamePasswordAuthenticationToken authenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                        /**
                         * Seteamos los detalles de la conexion que esta recibiendo
                         * Con el fin (Que navegador esta usando, horario se conecto, sistema operativo. etc)
                         */
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        //asignar autentificacion, par que luego no tenga que volver a pasar por toda la configuracion del filtro
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                    else {
                        SecurityContextHolder.clearContext();
                    }
                }
                else {
                    SecurityContextHolder.clearContext();
                }
            }

            //Terminando indicaremos que el filtro sea evaluado por 'doFilter'
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            this.exceptionServlet(response,e);

        }

    }

    /**
     * Metodo que escribe en json en la respuesta del servicio. en cada peticion
     * - simula que no esta autorizado.
     * @param response
     * @throws IOException
     */
    private void exceptionServlet(HttpServletResponse response, Throwable e) throws IOException {
        //https://stackoverflow.com/questions/7658881/setting-a-string-in-a-body-of-httpresponse
        //https://stackoverflow.com/questions/29340383/convert-map-to-json-using-jackson
        //https://stackoverflow.com/questions/2010990/how-do-you-return-a-json-object-from-a-java-servlet
//            https://www.toptal.com/java/spring-boot-rest-api-error-handling

        String ErrorServletJson = new ObjectMapper()
                .writeValueAsString(new ApiResponseError(HttpStatus.UNAUTHORIZED, e));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(ErrorServletJson);
        response.setContentType("application/json");
        response.getWriter().flush();
    }

    /**
     * Verifica si existe el Token o el Bearer en la peticion(header)
     * @param request
     * @param response
     * @return
     */
    private boolean existJWTToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader(KEY_HEADER_AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            return true;
        }
        return false;
    }

    /**
     * Extrae el contenido del token
     * @param request
     * @return
     */
    private String extractJWTToken(HttpServletRequest request){
        return request.getHeader(KEY_HEADER_AUTHORIZATION).replace(BEARER,"");
    }

}

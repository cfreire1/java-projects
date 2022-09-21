package com.example.api.web.config;

import com.example.api.web.config.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * - Spring
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 * https://docs.spring.io/spring-security/site/docs/5.0.x/api/org/springframework/security/core/userdetails/User.html#withDefaultPasswordEncoder--
 * https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
 * https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-dep-getting-started
 * https://www.baeldung.com/spring-security-expressions
 *
 * - Ejemplos de implmentacion
 *  https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/hello-security-explicit
 *
 *  - websecurityconfigureradapter (DEPRECATED)
 * https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
 * https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/hello-security-explicit
 *
 * - withDefaultPasswordEncoder (DEPRECATED)
 * https://stackoverflow.com/questions/49755413/authenticationmanagerbuilder-in-springboot-2-0-1-release
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults()) //Se comentan para desactivar el login(formulario)

                //==============================================================JWT
                //->suprimir config por defecto por:
                //desabilitar peticiones crusadas
                .csrf().disable()

                //Autorize las peticiones...
                // '/**/' : todas las peticiones... que terminen en: '/authenticate'
                //las permita '.permitAll();'
                .authorizeRequests()

                //controller auth
                .antMatchers("/auth/authenticate").permitAll()

                //Swagger 3
                .antMatchers("/swagger-ui/index.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()

                //todas las demas pidan autentificacion
                .anyRequest().authenticated()

                //==============================================================JWT-filter
                //El filtro 'JwtFilterRequest.java' sera el encargado de recibir todas las peticiones
                //y procesarlas

                .and().sessionManagement()

                // Indicaremos que  la session sera STATELESS (sin session o sin estado)
                // Por que los jwt son los que controlaran cada peticion en particular
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //agregar un filtro antes, y el filtro sera 'JwtFilterRequest'
        // y indicamos que tipo de filtro es 'UsernamePasswordAuthenticationFilter': es un filtro de usuario y contrasenna
        http.addFilterBefore(getJwtFilterRequest(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtFilterRequest getJwtFilterRequest(){
        return new JwtFilterRequest();
    }

}

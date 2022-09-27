package com.example.api.web.controller;

import com.example.api.domain.dto.AuthenticationRequest;
import com.example.api.domain.dto.AuthenticationResponse;
import com.example.api.domain.services.UserDetailsServiceImpl;
import com.example.api.web.config.JwtSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * - Idea
 * https://www.javainuse.com/spring/boot-jwt
 *
 * AuthenticationManager (bean AuthenticationManager esté en el contexto de spring)
 * https://stackoverflow.com/questions/71281032/spring-security-exposing-authenticationmanager-without-websecurityconfigureradap
 *
 */
@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtSecurityConfig jwtSecurityConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken (@RequestBody AuthenticationRequest request){
        try{
            //verificar si user y pass son correctos
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            //mandamos el user que esta dentro de la session
            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            //asignamos 'UserDetails' y respondemos con el token
            final String token = jwtSecurityConfig.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(token),HttpStatus.OK);
        }catch (BadCredentialsException e){
            throw  new BadCredentialsException("Existe un problema con las credenciales",e);
        }catch (InternalAuthenticationServiceException e){
            throw  new InternalAuthenticationServiceException("Existe un problema con el servidor",e);
        }
    }

}

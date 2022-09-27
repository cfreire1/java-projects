package com.example.springbootrestbasic.domain.services;

import com.example.springbootrestbasic.domain.dto.UsernameDTO;
import com.example.springbootrestbasic.domain.dummy.DataDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * - Se ocupo parte de la idea de implementacion
 * https://www.javadevjournal.com/spring/spring-security-userdetailsservice/
 *
 * Servicio que se encarga de validar si exite un usuario para ser logeado.
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DataDummy usernameMapper;

    /**
     * La interfaz UserDetailsService se utiliza para recuperar datos relacionados con el usuario. Tiene un método
     * llamado loadUserByUsername() que se puede sobreescribir para personalizar el proceso de búsqueda del usuario.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsernameDTO> usernameEntity = usernameMapper.findUsername(username);
        if (!usernameEntity.isPresent()) {throw new UsernameNotFoundException(username);}
        return usernameEntity
                .map(usernameEntity1 ->
                        User.builder()
                                .username(usernameEntity1.getUsername())
                                .password("{bcrypt}"+usernameEntity1.getPassword())
                                .roles(usernameEntity1.getRole())
                                .build()
                ).orElse(null);

    }
}

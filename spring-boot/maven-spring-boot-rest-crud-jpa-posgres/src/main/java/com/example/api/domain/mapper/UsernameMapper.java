package com.example.api.domain.mapper;

import com.example.api.domain.dto.UsernameDTO;
import com.example.api.persistence.entity.UsernameEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsernameMapper {

    public Optional<UsernameDTO> mapperEntityToDto (Optional<UsernameEntity> usernameEntity){
        return usernameEntity
                .map(mapEntity1 -> Optional.of(UsernameDTO.builder()
                                .username(mapEntity1.getUsername())
                                .password(mapEntity1.getPassword())
                                .role(mapEntity1.getRole())
                                .state(mapEntity1.isState())
                        .build()))
                .orElse(Optional.empty());
    }


}

package com.example.msbackendusers.infrastructure.mapper;

import com.example.msbackendusers.domain.model.Phone;
import com.example.msbackendusers.domain.model.Users;
import com.example.msbackendusers.infrastructure.controller.dto.PhoneDTO;
import com.example.msbackendusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.msbackendusers.infrastructure.controller.dto.UsersResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserControllerMapper {


    //Users
    public Users toDomain (UsersRequestDTO usersRequestDTO){
        return Users.builder()
                .name(usersRequestDTO.getName())
                .email(usersRequestDTO.getEmail())
                .password(usersRequestDTO.getPassword())
                .phones(toListDomain(usersRequestDTO.getPhones()))
                .build();
    }

    public UsersResponseDTO toDTO (Users users){
        return UsersResponseDTO.builder()
                .user(UsersRequestDTO.builder()
                        .name(users.getName())
                        .email(users.getEmail())
                        .password(users.getPassword())
                        .phones(toListDTO(users.getPhones()))
                        .build())
                .id(users.getIdUser().toString())
                .created(users.getCreated())
                .modified(users.getModified())
                .lastLogin(users.getLastLogin())
                .token(users.getTokenJwt())
                .isActive(users.isActive())
                .build();
    }

    //Phone
    public Phone toDomain (PhoneDTO usersRequestDTO){
        return Phone.builder()
                .number(Long.parseLong(usersRequestDTO.getNumber()))
                .cityCode(usersRequestDTO.getCitycode())
                .countryCode(usersRequestDTO.getContrycode())
                .build();
    }

    public PhoneDTO toDTO(Phone phone){
        return PhoneDTO.builder()
                .number(String.valueOf(phone.getNumber()))
                .citycode(phone.getCityCode())
                .contrycode(phone.getCountryCode())
                .build();
    }


    public List<Phone> toListDomain (List<PhoneDTO> phoneDTOList){
        return phoneDTOList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<PhoneDTO> toListDTO (List<Phone> phoneList){
        return phoneList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

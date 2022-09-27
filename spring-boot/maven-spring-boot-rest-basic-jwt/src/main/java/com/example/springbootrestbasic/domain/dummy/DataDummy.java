package com.example.springbootrestbasic.domain.dummy;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import com.example.springbootrestbasic.domain.dto.UsernameDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataDummy {
    public CategoryResponseDTO dummyCategoryResponseDTO() {
        return CategoryResponseDTO.builder()
                .id(1)
                .name("name")
                .state(true)
                .build();
    }
    public Optional<CategoryResponseDTO> dummyCategoryResponseDTOOptional(){
        return Optional.of(dummyCategoryResponseDTO());
    }

    public List<CategoryResponseDTO> dummyCategoryResponseDTOList(){
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryResponseDTOS.add(dummyCategoryResponseDTO());
        return categoryResponseDTOS;
    }

    //=================================================================

    public CategoryRequestDTO dummyCategoryRequestDTO() {
        return CategoryRequestDTO.builder()
                .id(1)
                .name("name")
                .state(true)
                .build();
    }


    public Optional<CategoryRequestDTO> dummyCategoryRequestDTOOptional() {
        return Optional.of(dummyCategoryRequestDTO());
    }

    //=================================================================TOKEN

    @Value("${api.secret.bd.users}")
    private String API_KEY;

    public UsernameDTO dummyUsernameDTO1(){
        return UsernameDTO.builder()
                .username("mario")
                .password(API_KEY)
                .role("USER")
                .build();
    }
    public UsernameDTO dummyUsernameDTO2(){
        return UsernameDTO.builder()
                .username("Juan")
                .password(API_KEY)
                .role("ADMIN")
                .build();
    }

    public Optional<UsernameDTO> findUsername(String username) {
        username = username.toLowerCase();//Por que sale la primera letra mayuscula...

        Map<String, Optional<UsernameDTO>> users = new HashMap<>();
        users.put("mario",Optional.of(this.dummyUsernameDTO1()));
        users.put("juan",Optional.of(this.dummyUsernameDTO2()));

        if (users.containsKey(username)){
            return users.get(username);
        }

        return Optional.empty();
    }

}

package com.example.javaspringbootrestusers.infrastructure.persistence.mapper;

import com.example.javaspringbootrestusers.domain.model.Phone;
import com.example.javaspringbootrestusers.infrastructure.persistence.entity.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneRepositoryMapper {
    public Phone toDomain (PhoneEntity phoneEntity){
        return Phone.builder()
                .number(phoneEntity.getNumber())
                .idUser(phoneEntity.getIdUser())
                .cityCode(phoneEntity.getCityCode())
                .countryCode(phoneEntity.getCountryCode())
                .build();
    }

    public PhoneEntity toEntity (Phone phone){
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setIdUser(phone.getIdUser());
        phoneEntity.setNumber(phone.getNumber());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setCountryCode(phone.getCountryCode());
        return phoneEntity;
    }

}

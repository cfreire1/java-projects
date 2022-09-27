package com.example.javaspringbootrestusers.infrastructure.persistence;

import com.example.javaspringbootrestusers.application.ports.persistence.PhoneRepository;
import com.example.javaspringbootrestusers.domain.model.Phone;
import com.example.javaspringbootrestusers.infrastructure.persistence.entity.PhoneEntity;
import com.example.javaspringbootrestusers.infrastructure.persistence.jpa.PhoneCrudRepository;
import com.example.javaspringbootrestusers.infrastructure.persistence.mapper.PhoneRepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneRepositoryImpl implements PhoneRepository {

    @Autowired
    private PhoneCrudRepository phoneCrudRepository;

    @Autowired
    private PhoneRepositoryMapper  mapper;

    @Override
    public Phone savePhone(Phone phone) {
        PhoneEntity phoneEntity =  mapper.toEntity(phone);
        return mapper.toDomain(phoneCrudRepository.save(phoneEntity));
    }

    @Override
    public boolean existPhone(long nphone) {
        return phoneCrudRepository.existsById(nphone);
    }
}

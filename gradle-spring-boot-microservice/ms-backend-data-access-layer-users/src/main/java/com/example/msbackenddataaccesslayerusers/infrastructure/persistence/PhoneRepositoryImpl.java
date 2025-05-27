package com.example.msbackenddataaccesslayerusers.infrastructure.persistence;

import com.example.msbackenddataaccesslayerusers.application.ports.persistence.PhoneRepository;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Phone;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.jpa.PhoneCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository {

    @Autowired
    private PhoneCrudRepository phoneCrudRepository;

    @Override
    public Phone savePhone(Phone phone) {
        return phoneCrudRepository.save(phone);
    }

    @Override
    public boolean existPhone(long nphone) {
        return phoneCrudRepository.existsById(nphone);
    }
}

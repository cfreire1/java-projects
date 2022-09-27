package com.example.javaspringbootrestusers.application.ports.persistence;

import com.example.javaspringbootrestusers.domain.model.Phone;

public interface PhoneRepository {
    Phone savePhone (Phone phoneEntity);
    boolean existPhone(long nphone);
}

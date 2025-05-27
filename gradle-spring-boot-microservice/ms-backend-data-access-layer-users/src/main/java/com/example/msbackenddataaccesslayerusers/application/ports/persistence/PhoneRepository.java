package com.example.msbackenddataaccesslayerusers.application.ports.persistence;

import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Phone;

public interface PhoneRepository {
    Phone savePhone (Phone phone);
    boolean existPhone(long nphone);
}

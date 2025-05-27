package com.example.msbackenddataaccesslayerusers.infrastructure.persistence.jpa;

import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneCrudRepository extends CrudRepository<Phone, Long> {
}

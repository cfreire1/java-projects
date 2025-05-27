package com.example.javaspringbootrestusers.infrastructure.persistence.jpa;

import com.example.javaspringbootrestusers.infrastructure.persistence.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhoneCrudRepository extends CrudRepository<PhoneEntity, Long> {
}

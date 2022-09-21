package com.example.javaspringbootrestusers.infrastructure.persistence.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PHONES")
public class PhoneEntity {
    @Id
    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "ID_USERS")
    private UUID idUser;

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "ID_USERS",insertable = false,updatable = false)
    private UsersEntity usersEntity;


    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }
}

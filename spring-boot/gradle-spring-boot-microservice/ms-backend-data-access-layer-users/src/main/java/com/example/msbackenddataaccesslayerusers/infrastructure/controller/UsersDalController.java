package com.example.msbackenddataaccesslayerusers.infrastructure.controller;

import com.example.msbackenddataaccesslayerusers.application.ports.persistence.PhoneRepository;
import com.example.msbackenddataaccesslayerusers.application.ports.persistence.UserRepository;
import com.example.msbackenddataaccesslayerusers.infrastructure.controller.dto.PhoneRq;
import com.example.msbackenddataaccesslayerusers.infrastructure.controller.dto.UsersRq;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Phone;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-dal")
@CrossOrigin(origins = "*")
public class UsersDalController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    //savePhone:post
    @PostMapping("/save-phone")
    public ResponseEntity<Phone> savePhone(@RequestBody Phone phone){
        return new ResponseEntity<>(phoneRepository.savePhone(phone), HttpStatus.OK);
    }

    //existPhone:get
    @PostMapping("/exist-phone")
    public ResponseEntity<Boolean> existPhone(@RequestBody PhoneRq phone){
        return new ResponseEntity<>(phoneRepository.existPhone(phone.getNumber()), HttpStatus.OK);
    }

    //saveUser:post
    @PostMapping("/save-user")
    public ResponseEntity<Users> saveUser(@RequestBody Users users){
        return new ResponseEntity<>(userRepository.saveUser(users), HttpStatus.OK);
    }


    //existEmail:get
    @PostMapping("/exist-email")
    public ResponseEntity<Boolean> existEmail(@RequestBody UsersRq users){
        return new ResponseEntity<>(userRepository.existEmail(users.getEmail()), HttpStatus.OK);
    }

}

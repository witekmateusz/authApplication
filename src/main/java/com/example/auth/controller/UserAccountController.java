package com.example.auth.controller;

import com.example.auth.entity.UserAccount;
import com.example.auth.repository.IUserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAccountController {

    @Autowired
    IUserAccountRepo userAccountRepo;

    @PostMapping("/users")
    public ResponseEntity<UserAccount> save(@RequestBody UserAccount userAccount) {

        try {
            return new ResponseEntity<>(userAccountRepo.save(userAccount), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getAllUsers(){
        try {
            List<UserAccount> list = userAccountRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<List<UserAccount>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<UserAccount>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAccount> getSingleCustomers(@PathVariable Long id){
        Optional<UserAccount> userAccount = userAccountRepo.findById(id);

        if(userAccount.isPresent()) {
            return new ResponseEntity<UserAccount>(userAccount.get(), HttpStatus.OK);
        }
        return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
    }
}

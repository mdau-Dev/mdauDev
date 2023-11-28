package com.mdauDev.SecurityCurity.controller;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.model.AuthenticationResponse;
import com.mdauDev.SecurityCurity.model.CustomerModel;
import com.mdauDev.SecurityCurity.model.LoginRequest;
import com.mdauDev.SecurityCurity.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class UnsecureController {

    @Autowired
    private CustomerServiceInterface service;

    @PostMapping(path = "/register")
    public ResponseEntity registerNewUser(@RequestBody CustomerModel model){

        return ResponseEntity.ok(service.registerNewUser(model));
    }
    @PostMapping("/login")
   public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/fetchAll")
    public List<Customer> fetchAll(@PathVariable("email")String email){
        return service.getAll();
    }
}

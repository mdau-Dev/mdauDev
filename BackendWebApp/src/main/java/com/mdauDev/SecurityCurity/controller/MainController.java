package com.mdauDev.SecurityCurity.controller;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.model.CustomerModel;
import com.mdauDev.SecurityCurity.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/main")
public class MainController {

   @Autowired
   private CustomerServiceInterface serviceInterface;

    @GetMapping(path = "/getAll")
    public List<Customer> fetchAll(){
        return serviceInterface.fetchAll();
    }
}

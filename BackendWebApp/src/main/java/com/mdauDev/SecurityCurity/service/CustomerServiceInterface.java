package com.mdauDev.SecurityCurity.service;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.Entity.Product;
import com.mdauDev.SecurityCurity.model.AuthenticationResponse;
import com.mdauDev.SecurityCurity.model.CustomerModel;
import com.mdauDev.SecurityCurity.model.LoginRequest;
import com.mdauDev.SecurityCurity.model.ProductModel;

import java.util.List;

public interface CustomerServiceInterface {
    List<Customer> fetchAll();

    AuthenticationResponse registerNewUser(CustomerModel model);

    Customer findTheCustomer(String email);

    void storeOrder(ProductModel model);


    AuthenticationResponse authenticate(LoginRequest request);

    List<Customer> getAll();
}

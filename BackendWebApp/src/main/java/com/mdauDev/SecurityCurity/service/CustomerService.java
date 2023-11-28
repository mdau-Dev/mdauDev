package com.mdauDev.SecurityCurity.service;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.Entity.Product;
import com.mdauDev.SecurityCurity.Entity.Role;
import com.mdauDev.SecurityCurity.auth.JwtService;
import com.mdauDev.SecurityCurity.model.AuthenticationResponse;
import com.mdauDev.SecurityCurity.model.CustomerModel;
import com.mdauDev.SecurityCurity.model.LoginRequest;
import com.mdauDev.SecurityCurity.model.ProductModel;
import com.mdauDev.SecurityCurity.repository.CustomerRepository;
import com.mdauDev.SecurityCurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomerService implements CustomerServiceInterface {

    public static final String NOT_FOUND = "The customer of the email %s does not exist";
    public static final String ALREADY_EXISTS = "YOU CANNOT USE THE EMAIL %s ";
    public static final String ID_NOT_FOUND = "The customer of the id %s does not exist";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    @Override
    public AuthenticationResponse registerNewUser(CustomerModel model) {
        Customer customer = new Customer();
        customer.setName(model.getName());

        Customer customer1=customerRepository.findCustomersByEmail(model.getEmail());
        if (customer1!=null){
            throw new IllegalStateException(String.format(ALREADY_EXISTS,model.getEmail()));
        }
        customer.setEmail(model.getEmail());
        customer.setRole(Role.USER);
        customer.setPassword(passwordEncoder.encode(model.getPassword()));

        customerRepository.save(customer);
        String token=jwtService.generateToken(customer);
        AuthenticationResponse auth=new AuthenticationResponse();
        auth.setToken(token);
        return auth;
    }
    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        Customer customer=customerRepository.findCustomersByEmail(request.getEmail());
        if (customer==null){
            throw new UsernameNotFoundException(String.format(NOT_FOUND,request.getEmail()));
        }
        String token= jwtService.generateToken(customer);
        AuthenticationResponse auth=new AuthenticationResponse();
        auth.setToken(token);
        return auth;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.giveAllCustomersByEmail();
    }

    @Override
    public Customer findTheCustomer(String email) {
        return customerRepository.findCustomersByEmail(email);
    }


    @Override
    public void storeOrder(ProductModel model) {
        Product product = new Product();
        product.setName(model.getName());
        product.setPrice(model.getPrice());
        product.setQuantity(model.getQuantity());

        productRepository.save(product);
    }



//
//    @Override
//    public List<Product> fetchAllProductsForOneCustmer(String email, Long id) {
//        List<Product> products=new ArrayList<>();
//
//        if (id!=null) {
//            boolean exists = existsProduct(id);
//            if (!exists) {
//                throw new IllegalStateException(String.format(ID_NOT_FOUND, id));
//            }
//            products = productRepository.findProductsByCustomerId(id);
//        }
//
//        if (id == null) {
//            id = productRepository.findIdOfCustomerFromEmail(email);
//            if (id == null) {
//                throw new IllegalStateException(String.format(ID_NOT_FOUND, id));
//            }
//            products = productRepository.findProductsByCustomerId(id);
//            if (products == null || products.isEmpty()) {
//                throw new IllegalStateException(String.format(ID_NOT_FOUND, id));
//            }
//        }
//        return products;
//    }

    private boolean existsProduct(Long id) {
        return productRepository.existsById(id);
    }
}

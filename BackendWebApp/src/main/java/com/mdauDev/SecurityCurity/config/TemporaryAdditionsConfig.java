package com.mdauDev.SecurityCurity.config;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.Entity.Product;
import com.mdauDev.SecurityCurity.Entity.Role;
import com.mdauDev.SecurityCurity.repository.CustomerRepository;
import com.mdauDev.SecurityCurity.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TemporaryAdditionsConfig {

    @Bean
    CommandLineRunner runner(ProductRepository productRepository){
        return args -> {

            Customer one=new Customer("Munene","mnene@gmail.com",
                    "12345", Role.USER,true);
            Customer teo=new Customer("Irene","irene@gmail.com",
                    "7864",Role.USER,true);

            Product pizza=new Product("pizza",1000,2,one);
            Product burger=new Product("burger",1500,3,teo);
            Product hotdog=new Product("hotdog",2000,2,one);
            Product mayonnaise=new Product("hotdog",3500,4,one);

            productRepository.saveAll(List.of(pizza,burger,hotdog,mayonnaise));
        };
    }
}

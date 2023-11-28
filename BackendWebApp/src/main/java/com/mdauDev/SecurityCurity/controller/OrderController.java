package com.mdauDev.SecurityCurity.controller;

import com.mdauDev.SecurityCurity.Entity.Product;
import com.mdauDev.SecurityCurity.model.ProductModel;
import com.mdauDev.SecurityCurity.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private CustomerServiceInterface serviceInterface;

    @PostMapping("/placeOrder")
    public String orderProducts(@RequestBody ProductModel model){
        serviceInterface.storeOrder(model);
        return "Order Successful";
    }

    @GetMapping("/getAllOrders/{email}")
    public List<Product> fetchAllProductsForOneCustmer(@PathVariable("email")String email,
                                                       @RequestParam("id")Long id){
        return null;
    }
}

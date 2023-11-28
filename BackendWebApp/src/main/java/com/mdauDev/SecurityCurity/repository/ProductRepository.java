package com.mdauDev.SecurityCurity.repository;

import com.mdauDev.SecurityCurity.Entity.Customer;
import com.mdauDev.SecurityCurity.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(
            nativeQuery = true,
            value = "select * from product where customer_product_id=: id"
    )
    List<Product> findProductsByCustomerId(@Param("id") Long id);
    @Query(
            nativeQuery = false,
            value = "select c.customerId from Customer c where c.email= :email "
    )
    Long findIdOfCustomerFromEmail(@Param("email")String email);
}

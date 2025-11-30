package com.ecommerce.PaymentService.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.PaymentService.entity.PaymentMethod;
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {}

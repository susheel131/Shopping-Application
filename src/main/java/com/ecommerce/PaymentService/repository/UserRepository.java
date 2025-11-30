package com.ecommerce.PaymentService.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.PaymentService.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {}


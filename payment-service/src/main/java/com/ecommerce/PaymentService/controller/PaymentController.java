package com.ecommerce.PaymentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.PaymentService.entity.Payment;
import com.ecommerce.PaymentService.entity.PaymentMethod;
import com.ecommerce.PaymentService.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping("/methods")
    public ResponseEntity<PaymentMethod> addMethod(@RequestParam Long userId, @RequestParam PaymentMethod.PaymentType type, @RequestParam String details) {
        try {
            PaymentMethod method = service.addPaymentMethod(userId, type, details);
            return ResponseEntity.ok(method);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/process")
    public ResponseEntity<Payment> process(@RequestParam Long userId, @RequestParam Long methodId, @RequestParam BigDecimal amount, @RequestParam String otp) {
        Payment payment = service.processPayment(userId, methodId, amount, otp);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Payment>> getHistory(@PathVariable Long userId) {
        List<Payment> history = service.getPaymentHistory(userId);
        return ResponseEntity.ok(history);
    }
}
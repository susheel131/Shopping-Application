package com.ecommerce.PaymentService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.PaymentService.config.EncryptionConfig;
import com.ecommerce.PaymentService.entity.Payment;
import com.ecommerce.PaymentService.entity.PaymentMethod;
import com.ecommerce.PaymentService.entity.User;
import com.ecommerce.PaymentService.repository.PaymentMethodRepository;
import com.ecommerce.PaymentService.repository.PaymentRepository;
import com.ecommerce.PaymentService.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PaymentMethodRepository methodRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    public PaymentMethod addPaymentMethod(Long userId, PaymentMethod.PaymentType type, String details) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        PaymentMethod method = new PaymentMethod();
        method.setUser(user);
        method.setType(type);
        method.setDetails(EncryptionConfig.encrypt(details));
        return methodRepo.save(method);
    }

    public Payment processPayment(Long userId, Long methodId, BigDecimal amount, String otp) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        PaymentMethod method = methodRepo.findById(methodId).orElseThrow(() -> new RuntimeException("Method not found"));

        // Fraud detection
        if (detectFraud(userId, amount)) {
            return savePayment(user, method, amount, Payment.PaymentStatus.FRAUD_DETECTED);
        }

        // 3D Secure
        if (!"123456".equals(otp)) {
            return savePayment(user, method, amount, Payment.PaymentStatus.FAILED);
        }

        // Mock processing
        Payment.PaymentStatus status = Math.random() > 0.1 ? Payment.PaymentStatus.SUCCESS : Payment.PaymentStatus.FAILED;
        return savePayment(user, method, amount, status);
    }

    private boolean detectFraud(Long userId, BigDecimal amount) {
        List<Payment> failedPayments = paymentRepo.findByUserId(userId).stream()
            .filter(p -> p.getStatus() == Payment.PaymentStatus.FAILED).toList();
        return amount.compareTo(BigDecimal.valueOf(1000)) > 0 || failedPayments.size() > 2;
    }

    private Payment savePayment(User user, PaymentMethod method, BigDecimal amount, Payment.PaymentStatus status) {
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setMethod(method);
        payment.setAmount(amount);
        payment.setStatus(status);
        payment.setTimestamp(LocalDateTime.now());
        return paymentRepo.save(payment);
    }

    public List<Payment> getPaymentHistory(Long userId) {
        return paymentRepo.findByUserId(userId);
    }
}

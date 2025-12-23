package com.ecommerce.userServices.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.userServices.dto.AddressDTO;
import com.ecommerce.userServices.dto.UserProfileDTO;
import com.ecommerce.userServices.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@RequestHeader("X-User-Id") String email) {
        Long userId = userService.getUserIdByEmail(email);
        return ResponseEntity.ok(userService.getProfile(userId));
    }
    
    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(
            @RequestHeader("X-User-Id") String email,
            @RequestBody UserProfileDTO dto) {
    	Long userId = userService.getUserIdByEmail(email);
        return ResponseEntity.ok(userService.updateProfile(userId, dto));
    }
    
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(
            @RequestHeader("X-User-Id") String email,
            @RequestBody AddressDTO dto) {
    	Long userId = userService.getUserIdByEmail(email);
        return ResponseEntity.ok(userService.addAddress(userId, dto));
    }
    
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(@RequestHeader("X-User-Id") String email) {
    	Long userId = userService.getUserIdByEmail(email);
        return ResponseEntity.ok(userService.getAddresses(userId));
    }
}

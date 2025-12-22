package com.ecommerce.userServices.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.userServices.dto.AddressDTO;
import com.ecommerce.userServices.dto.AuthResponse;
import com.ecommerce.userServices.dto.LoginRequest;
import com.ecommerce.userServices.dto.RegisterRequest;
import com.ecommerce.userServices.dto.UserProfileDTO;
import com.ecommerce.userServices.entity.Address;
import com.ecommerce.userServices.entity.Role;
import com.ecommerce.userServices.entity.User;
import com.ecommerce.userServices.repository.AddressRepository;
import com.ecommerce.userServices.repository.UserRepository;
import com.ecommerce.userServices.security.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRole(Role.CUSTOMER);
        
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole().name());
        
        return new AuthResponse(token, "Bearer", user.getId(), user.getEmail(), user.getRole().name());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole().name());
        
        return new AuthResponse(token, "Bearer", user.getId(), user.getEmail(), user.getRole().name());
    }
    
    public UserProfileDTO getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return mapToProfileDTO(user);
    }
    
    @Transactional
    public UserProfileDTO updateProfile(Long userId, UserProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        
        user = userRepository.save(user);
        return mapToProfileDTO(user);
    }
    
    @Transactional
    public AddressDTO addAddress(Long userId, AddressDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setCountry(dto.getCountry());
        address.setIsDefault(dto.getIsDefault());
        address.setUser(user);
        
        if (dto.getIsDefault()) {
            user.getAddresses().forEach(a -> a.setIsDefault(false));
        }
        
        address = addressRepository.save(address);
        return mapToAddressDTO(address);
    }
    
    public List<AddressDTO> getAddresses(Long userId) {
        return addressRepository.findByUserId(userId).stream()
                .map(this::mapToAddressDTO)
                .collect(Collectors.toList());
    }
    
    private UserProfileDTO mapToProfileDTO(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setAddresses(user.getAddresses().stream()
                .map(this::mapToAddressDTO)
                .collect(Collectors.toList()));
        return dto;
    }
    
    private AddressDTO mapToAddressDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        dto.setCountry(address.getCountry());
        dto.setIsDefault(address.getIsDefault());
        return dto;
    }
}
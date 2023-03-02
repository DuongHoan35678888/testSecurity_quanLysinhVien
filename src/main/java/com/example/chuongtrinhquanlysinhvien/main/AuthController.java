package com.example.chuongtrinhquanlysinhvien.main;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subs")
    private ResponseEntity<?> subcribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String maSinhVien = authenticationRequest.getMaSinhVien();
        String password = authenticationRequest.getPassword();
        User user = new User();
        user.setMaSinhVien(maSinhVien);
        user.setPassword(password);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error during client Subscription" + maSinhVien));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Succesful Subcription for client" + maSinhVien));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String maSinhVien = authenticationRequest.getMaSinhVien();
        String password = authenticationRequest.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(maSinhVien, password));
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication" + maSinhVien));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Succesful Authentication for client" + maSinhVien));
    }
}

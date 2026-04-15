package com.raka.authorization.service;

import com.raka.authorization.dto.AuthRequest;
import com.raka.authorization.dto.AuthResponse;
import com.raka.authorization.model.User;
import com.raka.authorization.repository.UserRepository;
import com.raka.authorization.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        // Buat user baru & enkripsi passwordnya
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();
        repository.save(user);

        // Buat token langsung setelah register
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(AuthRequest request) {
        // Autentikasi ke Spring Security (akan melempar error jika password salah)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Ambil data user dari DB
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        
        // Buat token
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
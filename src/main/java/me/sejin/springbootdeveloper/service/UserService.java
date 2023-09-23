package me.sejin.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sejin.springbootdeveloper.domain.User;
import me.sejin.springbootdeveloper.dto.AddUserRequest;
import me.sejin.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
   /* private final BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 1. 패스워드 암호화
               /* .password(bCryptPasswordEncoder.encode(dto.getPassword()))*/
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}

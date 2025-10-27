package com.tad.service.impl;

import com.tad.dto.exception.TadAPIException;
import com.tad.dto.message.RegisterDto;
import com.tad.entity.Role;
import com.tad.entity.User;
import com.tad.repositories.RoleRepository;
import com.tad.repositories.UserRepository;
import com.tad.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public String register(RegisterDto registerDto) {
        // check thong tin Username co ton tai trong database hay khong
        if(this.userRepository.existsByUsername(registerDto.getUsername())){
            throw new TadAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // check thong tin Email co ton tai trong database hay khong
        if(this.userRepository.existsByEmail(registerDto.getEmail())){
            throw new TadAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setPhone(registerDto.getPhone());
        user.setGender(registerDto.getGender());
        user.setDateOfBirth(registerDto.getDateOfBirth());

        Set<Role> roles = new HashSet<>();
        Role userRole = this.roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User register successfully!";

    }
}

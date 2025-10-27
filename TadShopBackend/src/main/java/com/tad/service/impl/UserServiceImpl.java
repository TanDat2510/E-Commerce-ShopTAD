package com.tad.service.impl;

import com.tad.dto.exception.TadAPIException;
import com.tad.dto.mapper.UserMapper;
import com.tad.dto.model.UserDto;
import com.tad.dto.response.ObjectResponse;
import com.tad.entity.Role;
import com.tad.entity.User;
import com.tad.repositories.RoleRepository;
import com.tad.repositories.UserRepository;
import com.tad.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        // check thong tin Username co ton tai trong database hay khong
        if(this.userRepository.existsByUsername(userDto.getUsername())){
            throw new TadAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // check thong tin Email co ton tai trong database hay khong
        if(this.userRepository.existsByEmail(userDto.getEmail())){
            throw new TadAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User newUser = this.userMapper.mapToEntity(userDto);
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = this.roleRepository.findByName("USER").get();
        roles.add(userRole);
        newUser.setRoles(roles);

        User userResponse = this.userRepository.save(newUser);
        return this.userMapper.mapToDto(userResponse);
    }

    @Override
    public ObjectResponse<UserDto> getAllUser(int pageSize, int pageNo, String sortBy, String sortDir) {
        // Tao 1 object Sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // Tao Pageable de phan trang
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tim tat ca cac trang
        Page<User> pages = this.userRepository.findAll(pageable);

        // Lay ra content cua cac trang
        List<User> users = pages.getContent();

        // Ep kieu sang UserDto
        List<UserDto> content = users.stream().map(user -> this.userMapper.mapToDto(user)).collect(Collectors.toList());

        ObjectResponse<UserDto> response = new ObjectResponse<>();
        response.setContent(content);
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalElements(pages.getTotalElements());
        response.setLast(pages.isLast());
        response.setTotalPages(pages.getTotalPages());
        return response;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserDto getUserByUsername(String userName) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDto getUserProfile(Long userId) {
        return null;
    }

    @Override
    public ObjectResponse<UserDto> searchUser(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }
}

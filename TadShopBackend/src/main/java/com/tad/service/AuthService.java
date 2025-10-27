package com.tad.service;

import com.tad.dto.message.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}

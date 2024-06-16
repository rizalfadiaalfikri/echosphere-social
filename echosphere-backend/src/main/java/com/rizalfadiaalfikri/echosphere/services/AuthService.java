package com.rizalfadiaalfikri.echosphere.services;

import com.rizalfadiaalfikri.echosphere.models.req.LoginDto;
import com.rizalfadiaalfikri.echosphere.models.res.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginDto dto);
}

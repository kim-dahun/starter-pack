package com.service.user.service;

import com.service.user.vo.response.TokenResponse;

import java.util.List;

public interface AuthServiceClient {
    TokenResponse getUserToken(String userId, String comCd, List<String> roles);

    boolean validateToken(String token);
}

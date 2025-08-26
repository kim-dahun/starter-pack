package com.service.user.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private String refreshToken;
    private String scope;
}

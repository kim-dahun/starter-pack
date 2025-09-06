package com.service.user.api.vo.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenRequest {
    private String userId;
    private String comCd;
    private List<String> roles;
}

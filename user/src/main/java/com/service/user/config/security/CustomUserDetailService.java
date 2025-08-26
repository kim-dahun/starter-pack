package com.service.user.config.security;

import com.service.user.entity.id.UserAuthId;
import com.service.user.repository.UserAuthRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // 요청에서 comCd 가져오기 (헤더나 속성에서)
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String comCd = request.getHeader("comCd");

            if (comCd == null) {
                comCd = (String) request.getAttribute("comCd");
            }

            // comCd가 없으면 기본값 설정 (또는 예외 처리)
            if (comCd == null) {
                throw new UsernameNotFoundException("Company code not provided");
            }

            // 복합 키로 사용자 조회
            return userAuthRepository.findById(
                    new UserAuthId(comCd, username)
            ).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error loading user: " + e.getMessage(), e);
        }
    }

}

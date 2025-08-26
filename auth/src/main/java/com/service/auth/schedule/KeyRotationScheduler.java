package com.service.auth.schedule;

import com.service.auth.service.KeyManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class KeyRotationScheduler {

    private final KeyManagementService keyManagementService;

    @Value("${key-management.rotation.enabled:false}")
    private Boolean enable;

    @Value("${key-management.rotation.cron}")
    private String cron;

    /**
     * 정기적으로 키 순환 (예: 90일마다)
     * cron = "초 분 시 일 월 요일"
     */
    @Scheduled(cron = "0 0 0 1 */3 *")  // 3개월마다 1일 자정에 실행
    public void rotateKeys() {
        if(enable) {
            log.info("Rotating OAuth2 keys");
            keyManagementService.rotateKeys();
            log.info("OAuth2 keys rotated successfully");
        }
    }

}

package com.service.auth.service.impl;

import com.nimbusds.jose.jwk.RSAKey;
import com.service.auth.entity.OAuthKey;
import com.service.auth.repository.OAuthKeyRepository;
import com.service.auth.service.KeyManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyManagementServiceImplV1 implements KeyManagementService {

    private final OAuthKeyRepository keyRepository;

    /**
     * 활성화된 키를 찾거나 새 키 생성
     */
    @Transactional
    @Override
    public RSAKey getOrCreateActiveKey() {
        Optional<OAuthKey> activeKeyOpt = keyRepository.findByActiveTrue();

        if (activeKeyOpt.isPresent()) {
            OAuthKey activeKey = activeKeyOpt.get();
            return convertToRSAKey(activeKey);
        } else {
            // 활성화된 키가 없으면 새 키 생성
            return createAndSaveNewKey();
        }
    }

    /**
     * 새 RSA 키 생성 및 저장
     */
    @Transactional
    @Override
    public RSAKey createAndSaveNewKey() {
        try {
            // 기존 활성 키를 비활성화
            keyRepository.findByActiveTrue().ifPresent(key -> {
                key.setActive(false);
                keyRepository.save(key);
            });

            // 새 RSA 키 생성
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            // 인코딩
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            // 키 ID 생성
            String keyId = UUID.randomUUID().toString();

            // DB 저장
            OAuthKey oAuthKey = OAuthKey.builder()
                    .keyId(keyId)
                    .publicKey(publicKeyStr)
                    .privateKey(privateKeyStr)
                    .createTime(LocalDateTime.now())
                    .active(true)
                    .build();

            keyRepository.save(oAuthKey);

            // RSAKey 반환
            return new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(keyId)
                    .build();

        } catch (Exception e) {
            log.error("Failed to create new RSA key", e);
            throw new RuntimeException("Failed to create new RSA key", e);
        }
    }

    /**
     * DB에서 로드한 키를 RSAKey 객체로 변환
     */
    private RSAKey convertToRSAKey(OAuthKey oAuthKey) {
        try {
            // 디코딩
            byte[] publicKeyBytes = Base64.getDecoder().decode(oAuthKey.getPublicKey());
            byte[] privateKeyBytes = Base64.getDecoder().decode(oAuthKey.getPrivateKey());

            // 키 팩토리
            java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");

            // 공개키 복원
            java.security.spec.X509EncodedKeySpec publicKeySpec = new java.security.spec.X509EncodedKeySpec(publicKeyBytes);
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

            // 개인키 복원
            java.security.spec.PKCS8EncodedKeySpec privateKeySpec = new java.security.spec.PKCS8EncodedKeySpec(privateKeyBytes);
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            // RSAKey 생성
            return new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(oAuthKey.getKeyId())
                    .build();

        } catch (Exception e) {
            log.error("Failed to convert saved key to RSAKey", e);
            throw new RuntimeException("Failed to convert saved key to RSAKey", e);
        }
    }

    /**
     * 키 순환 (rotation) - 필요 시 호출
     */
    @Transactional
    @Override
    public RSAKey rotateKeys() {
        return createAndSaveNewKey();
    }

    /**
     * 특정 키 ID로 키 조회
     */
    @Override
    public Optional<RSAKey> findByKeyId(String keyId) {
        return keyRepository.findByKeyId(keyId)
                .map(this::convertToRSAKey);
    }

}

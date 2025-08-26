package com.service.auth.service;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface KeyManagementService {

    RSAKey getOrCreateActiveKey();

    RSAKey createAndSaveNewKey();

    RSAKey rotateKeys();

    Optional<RSAKey> findByKeyId(String keyId);
}

package com.service.auth.repository;

import com.service.auth.entity.OAuthKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthKeyRepository extends JpaRepository<OAuthKey, Long> {

    Optional<OAuthKey> findByActiveTrue();
    Optional<OAuthKey> findByKeyId(String keyId);

}

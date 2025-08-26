package com.service.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AUTH_KEY", schema = "USER_MANAGE")
public class OAuthKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "KEY_ID", nullable = false, unique = true)
    private String keyId;

    @Lob
    @Column(name = "PUBLIC_KEY", nullable = false, columnDefinition = "LONGTEXT")
    private String publicKey;

    @Lob
    @Column(name = "PRIVATE_KEY", nullable = false, columnDefinition = "LONGTEXT")
    private String privateKey;

    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

}

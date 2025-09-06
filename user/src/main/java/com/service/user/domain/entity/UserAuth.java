package com.service.user.domain.entity;

import com.service.user.domain.entity.id.UserInfoId;
import com.starter.lib.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(UserInfoId.class)
@Entity
@Table(name = "USER_AUTH", schema = "USER_MANAGE")
public class UserAuth extends CmnBaseCUDEntity implements UserDetails {

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "COM_CD", nullable = false, length = 50)
    private String comCd;

    @Id
    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "USER_ID", nullable = false, length = 50)
    private String userId;

    @jakarta.validation.constraints.Size(max = 150)
    @Column(name = "USER_PASSWORD", length = 150)
    private String userPassword;

    @jakarta.validation.constraints.Size(max = 100)
    @Column(name = "STATUS", length = 100)
    private String status;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    // 필요시 UserInfo 참조 추가 (단방향)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false),
            @JoinColumn(name = "COM_CD", referencedColumnName = "COM_CD", insertable = false, updatable = false)
    })
    private UserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_password_expires_at")
    private LocalDateTime resetPasswordExpiresAt;

    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "activation_token_expires_at")
    private LocalDateTime activationTokenExpiresAt;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean enabled = false;

    // ✅ جديد
    @Column(name = "must_change_password", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean mustChangePassword = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "technicien_categories",
            joinColumns = @JoinColumn(name = "technicien_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private List<CategorieReclamation> categories = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getDisplayName() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) return List.of();
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
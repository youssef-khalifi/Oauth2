package com.youssef.security_tp2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for better compatibility
    private Integer id;

    @Column(unique = true, nullable = false) // Ensure username is not null
    private String username;

    @Column(nullable = false) // Ensure password is not null
    private String password;

    @Column(nullable = false) // Ensure enabled is not null
    private boolean enabled = true; // Default value can be set

    @ElementCollection(fetch = EAGER)
    @Enumerated(EnumType.STRING) // Store the enum as a string
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")) // Optional: Create a join table
    private List<Role> roles = new ArrayList<>(); // Initialize to avoid null

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement as needed
    }

    @Override
    public boolean isEnabled() {
        return enabled; // Return the value of the enabled field
    }
}

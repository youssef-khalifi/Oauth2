package com.youssef.security_tp2.configuration;


import com.youssef.security_tp2.entities.Compte;
import com.youssef.security_tp2.entities.Role;
import com.youssef.security_tp2.entities.User;
import com.youssef.security_tp2.repositories.CompteRepositoty;
import com.youssef.security_tp2.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompteRepositoty compteRepositoty;

    @PostConstruct
    public void initialize() {
        // Create the admin user
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEnabled(true);
        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(Role.ADMIN);
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        // Create a regular user
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEnabled(true);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        userRepository.save(user);

        Compte compte1 = new Compte();
        compte1.setNom("Youssef khalifi");
        compte1.setTel("0617478157");
        compte1.setMontant(1452);
        compteRepositoty.save(compte1);

        Compte compte2 = new Compte();
        compte2.setNom("ali ali");
        compte2.setTel("0617478157");
        compte2.setMontant(1452);
        compteRepositoty.save(compte2);
    }

}

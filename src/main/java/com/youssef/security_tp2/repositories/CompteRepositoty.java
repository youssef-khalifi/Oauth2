package com.youssef.security_tp2.repositories;


import com.youssef.security_tp2.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepositoty extends JpaRepository<Compte,Long> {

}

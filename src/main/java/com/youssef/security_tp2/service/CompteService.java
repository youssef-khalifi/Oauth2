package com.youssef.security_tp2.service;


import com.youssef.security_tp2.entities.Compte;
import com.youssef.security_tp2.repositories.CompteRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepositoty compteRepository;

    public Compte creerCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Optional<Compte> consulterSolde(Long id) {
        return compteRepository.findById(id);
    }

    public Compte crediter(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    public Compte debiter(Long id, double montant) {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        if (compte.getSolde() < montant) {
            throw new RuntimeException("Fonds insuffisants");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }

    public List<Compte> recupererTousLesComptes() {
        return compteRepository.findAll();
    }


}

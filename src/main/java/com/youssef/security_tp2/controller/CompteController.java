package com.youssef.security_tp2.controller;


import com.youssef.security_tp2.entities.Compte;
import com.youssef.security_tp2.service.CompteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/Comptes")
@RequiredArgsConstructor
public class CompteController {


    private final CompteService compteService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Compte> creerCompte(@RequestBody Compte compte) {
        Compte nouveauCompte = compteService.creerCompte(compte);
        return ResponseEntity.ok(nouveauCompte);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/solde")
    public ResponseEntity<Double> consulterSolde(@PathVariable Long id) {
        Optional<Compte> compte = compteService.consulterSolde(id);
        return compte.map(value -> ResponseEntity.ok(value.getSolde()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/crediter/{montant}")
    public ResponseEntity<Compte> crediter(@PathVariable Long id, @PathVariable double montant) {
        Compte compte = compteService.crediter(id, montant);
        return ResponseEntity.ok(compte);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/debiter/{montant}")
    public ResponseEntity<Compte> debiter(@PathVariable Long id, @PathVariable double montant) {
        Compte compte = compteService.debiter(id, montant);
        return ResponseEntity.ok(compte);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Compte>> recupererTousLesComptes() {
        List<Compte> comptes = compteService.recupererTousLesComptes();
        return ResponseEntity.ok(comptes);
    }

}

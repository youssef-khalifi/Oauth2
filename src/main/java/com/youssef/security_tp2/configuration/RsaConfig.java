package com.youssef.security_tp2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties("rsa")

public record RsaConfig(RSAPublicKey publicKey , RSAPrivateKey privateKey) {
}

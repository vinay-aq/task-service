package com.clientmanagement.taskservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.nio.charset.StandardCharsets;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {
    @Bean
    public JwtDecoder jwtDecoder() throws Exception {

        ClassPathResource resource =
                new ClassPathResource("keys/public_key.pem");

        String key = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        String publicKeyContent = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder()
                .decode(publicKeyContent);

        X509EncodedKeySpec keySpec =
                new X509EncodedKeySpec(decoded);

        KeyFactory keyFactory =
                KeyFactory.getInstance("RSA");

        RSAPublicKey publicKey =
                (RSAPublicKey) keyFactory.generatePublic(keySpec);

        return NimbusJwtDecoder
                .withPublicKey(publicKey)
                .build();


    }
}

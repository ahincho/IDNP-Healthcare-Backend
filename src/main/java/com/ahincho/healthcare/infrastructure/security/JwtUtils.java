package com.ahincho.healthcare.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secret;
    @Value("${jwt.time.expiration}")
    private String expirationTime;
}

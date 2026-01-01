package org.shortify.shortifybackend.security.jwt;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
}

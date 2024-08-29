package com.pragma.restaurantcrud.infrastructure.config.securityClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final ITokenUser tokenUser;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            List<String> roles = claims.get("auth", List.class);
            return new UsernamePasswordAuthenticationToken(email, null,
                    roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Failed to parse JWT", e);
        }
    }
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return tokenUser.isValidToken("Bearer "+token);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

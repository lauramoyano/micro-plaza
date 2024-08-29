package com.pragma.restaurantcrud.infrastructure.output.token;

import com.pragma.restaurantcrud.domain.spi.servicePortClient.ITokenService;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements ITokenService {
    private final JwtProvider jwtProvider;

    @Override
    public String getEmailFromToken(String token) {
       String email = token.replace("Bearer ", "").trim();
         return jwtProvider.getAuthentication(email).getName();
    }

    @Override
    public String getTokenPrefix() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}

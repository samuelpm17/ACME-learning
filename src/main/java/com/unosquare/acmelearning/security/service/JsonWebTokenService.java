package com.unosquare.acmelearning.security.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultJwtParser;

@Service
public class JsonWebTokenService {
    public String getUserNameFromRequest(String authorizationParam) throws ApplicationException {
        String token = authorizationParam.substring("Bearer ".length());
        
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    private <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver)
            throws ApplicationException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) throws ApplicationException {
        final String[] splitToken = token.split("\\.");
        final String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
        final DefaultJwtParser parser = new DefaultJwtParser();

        final Jwt<?, ?> jwt = parser.parse(unsignedToken);
        return (Claims) jwt.getBody();
    }
}

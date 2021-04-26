package com.unosquare.acmelearning.security.service;

import org.springframework.stereotype.Service;

import com.unosquare.acmelearning.exception.ApplicationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultJwtParser;

@Service
public class JsonWebTokenService {
    public String getUserNameFromRequest(String authorizationParam) throws ApplicationException {
        String token = authorizationParam.substring("Bearer ".length());
        
        return getClaimFromToken(token, "user_name");
    }
    
    private String getClaimFromToken(final String token, String claimName)
            throws ApplicationException {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(claimName, String.class);
    }

    private Claims getAllClaimsFromToken(final String token) throws ApplicationException {
        final String[] splitToken = token.split("\\.");
        final String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
        final DefaultJwtParser parser = new DefaultJwtParser();

        final Jwt<?, ?> jwt = parser.parse(unsignedToken);
        return (Claims) jwt.getBody();
    }
}

package com.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtVerify {
    
    private static final Logger LOG = LoggerFactory.getLogger(JwtVerify.class);
    private static final String SECRET_KEY = "keysecretforjwt"; // Replace with your actual secret key
    private final JWTVerifier verifier;

    public JwtVerify() {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        this.verifier = JWT.require(algorithm).build();
    }

    public boolean verifyToken(String AuthorizationHeader) {
        try {
            String token = AuthorizationHeader.replace("Bearer ", "");
            DecodedJWT jwt = new JWT().decode(token);
            verifier.verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            LOG.error("Invalid token", e);
            return false;
        }
    }

    private boolean isTokenExpired(DecodedJWT jwt) {
        system.out.println("Token claim time: " + jwt.getExpiresAt());
        system.out.println("Token claim name" + jwt.getClaim("name").asString());
    }
}

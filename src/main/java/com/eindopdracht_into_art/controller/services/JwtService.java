package com.eindopdracht_into_art.controller.services;

import com.eindopdracht_into_art.model.dtos.AuthRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final long EXPIRE_DURATION
            = 1000 * 60 * 60 * 24; // token is 24 uur geldig (in milliseconds)
    @Value("${company.name}")
    private String COMPANY_NAME;
    @Value("${jwt.secret}")
    private String KEY;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(AuthRequestDto dto) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, dto.getPassword());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //region Private methods

    private String createToken(Map<String, Object> claims, String subject) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(COMPANY_NAME)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T>
            claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
    //endregion
}

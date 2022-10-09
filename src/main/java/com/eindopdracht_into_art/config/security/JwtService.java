package com.eindopdracht_into_art.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.eindopdracht_into_art.config.security.SecurityConstants.EXPIRE_HRS_24;
import static com.eindopdracht_into_art.helpers.Randomizer.generateRandomizedByteArrayAndBase64EncodeToString;


@Service
public class JwtService {

    @Value("${company.name}")
    private static String ISSUER;

    private static final String KEY = generateRandomizedByteArrayAndBase64EncodeToString();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + EXPIRE_HRS_24))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(KEY))
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
        return Jwts.parser().setSigningKey(TextCodec.BASE64.decode(KEY)).parseClaimsJws(token).getBody();
    }

}

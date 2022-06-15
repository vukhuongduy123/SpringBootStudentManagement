package com.example.student.management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtils {
    private static final String SECRET_KEY = "@ThisIsJWTSecretKey";
    private static final int EXPIRED_TIME = 3600 * 1000;

    public String getUserName(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaims(token));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !getExpiration(token).before(new Date()));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        return createToken(userDetails.getUsername(), claimsMap);
    }

    private String createToken(String username, Map<String, Object> claimsMap) {
        Date currentTime = new Date(System.currentTimeMillis());
        return Jwts.builder().setClaims(claimsMap).setSubject(username).setIssuedAt(currentTime)
                .setExpiration(new Date(currentTime.getTime() + EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}

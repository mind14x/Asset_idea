package com.asset.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private static final byte[] SECRET_KEY_BYTES = "YourSuperSecretKeyHere1234567890123456789012".getBytes(StandardCharsets.UTF_8); // 长度至少256位
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_BYTES); // 使用固定密钥

    public static String generateToken(String userName, String role, int expirationInSeconds) {

        Date expirationDate = new Date(System.currentTimeMillis() + expirationInSeconds * 1000);

        return Jwts.builder()
                .claim("userName", userName)
                .claim("role", role)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    // Other JWT related methods (like validateToken, etc.)
    public static String getUserUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("userName", String.class);
    }

    public static String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("role", String.class);
    }



}











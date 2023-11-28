package com.example.util;

import static com.example.util.JwtUtil.Time.HOUR;
import static com.example.util.JwtUtil.Time.MINUTE;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

public class JwtUtil {

    private static final String secret =
            "e64159310c312e7d96e497b67cf2a388a5fcf9be4dd38ac709c491680961e7ce1540aa95d8c557758b80afbd7a3a6322bbcb268134e11d19b41d893e617c1fe9";
    private static final long expiration = 10000L;
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    private static final String issuer = "com.example";

    /**
     * “iss” (Issuer): Identifies the entity that issued the token.
     * “sub” (Subject): Identifies the subject of the token, typically a user or a client.
     * “aud” (Audience): Identifies the recipients that the token is intended for.
     * “exp” (Expiration Time): Identifies the token’s expiration time, after which it should not be accepted.
     * “nbf” (Not Before): Identifies the time before the token should not be accepted.
     * “iat” (Issued At): Identifies the time at which the token was issued.
     * “jti” (JWT ID): Provides a unique identifier for the token.
     */
    public static String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claim("authorities", "ROLE_ADMIN")
                .signWith(secretKey)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Object getSubject(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
    }

    public String generateJwt(String sub, Map<String, Object> customClaims) {
        return generateJwt(sub, customClaims, getExpiration(HOUR, 1));
    }

    public String generateRefreshJwt(String sub, String accessToken) {
        Key secretKey = Keys.hmacShaKeyFor(accessToken.getBytes());
        return generateJwt(sub, new HashMap<>(), getExpiration(HOUR, 2), secretKey);
    }

    public String generateJwt(String sub, Map<String, Object> customClaims, Date expiration) {
        return generateJwt(sub, customClaims, expiration, secretKey);
    }

    public String generateJwt(String sub, Map<String, Object> customClaims, Date expiration, Key secretKey) {
        Claims claims =
                Jwts.claims().subject(sub).expiration(expiration).issuer(issuer).build();
        claims.putAll(customClaims);

        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }

    private Date getExpiration(Time field, Integer amount) {
        Calendar calendar = Calendar.getInstance();
        // 預設 Token 過期設定為 1 小時
        int calenderField = Calendar.HOUR;
        int amount1 = 1;
        if (amount != null && field != null) {
            if (MINUTE.equals(field)) {
                calenderField = Calendar.MINUTE;
                amount1 = amount;
            } else if (HOUR.equals(field)) {
                amount1 = amount;
            }
        }
        calendar.add(calenderField, amount1);
        return calendar.getTime();
    }

    enum Time {
        MINUTE,
        HOUR,
        DAY,
    }
}

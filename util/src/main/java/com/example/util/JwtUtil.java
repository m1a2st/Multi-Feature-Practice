package com.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String CLAIMS_KEY_USER_ROLES = "userRoles";
    private String jwtSignKey;
    private long jwtExpireTimeAsSec;

//    public String createToken(String userName) {
//        String token = Jwts.builder()
//                .setSubject(userName)
//                .setIssuedAt(new Date()) //產生 JWT 的時間
//                .setExpiration(Date.from(Instant.now().plusSeconds(jwtExpireTimeAsSec))) // JWT 過期時間
//                .signWith(Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
//                .compact();
//        log.debug("token : {}", token);
//        return token;
//    }
//
//
//    /**
//     * 當 token 解析失敗時，會丟出對應的 Exception。一般來說會遇到失敗是因為 token 過期、token 內容被竄改。
//     *
//     * @param token
//     * @return
//     */
//    private Claims parseToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8)))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims;
//    }
//
//    public String parseUserNameFromToken(String token) {
//        return parseToken(token).getSubject();
//    }
//
//    public List<SimpleGrantedAuthority> parseUserAuthoritiesFromToken(String token) {
//        List<String> userRoles = parseToken(token).get(CLAIMS_KEY_USER_ROLES, List.class);
//        return userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
}

package com.three2one.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    final String CLAIMS_SUBJECT = "sub";
    final String CLAIMS_CREATED = "created";
    @Value("${aut.secret}")
    private String TOKEN_SECRET;
    @Value("${auth.expiration}")
    private Long TOKEN_VALIDITY=60544L;


    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put(CLAIMS_SUBJECT,userDetails.getUsername());
        claims.put(CLAIMS_CREATED,new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    public String getEmailFromToken(String token){
         try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        }catch (Exception ex) {
            return null;
        }
    }
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+TOKEN_VALIDITY);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = getEmailFromToken(token);

        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex) {
            claims = null;
        }

        return claims;
    }
}

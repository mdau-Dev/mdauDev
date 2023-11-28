package com.mdauDev.SecurityCurity.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Here we will extract and generate  and validate our token
 * Class is basically created for extraction of username
 */
@Service
public class JwtService {
    public static final String SECRET_KEY="232a19aee6cd8bfe0523aed2e7277456dd50993c0952a55fa736e120542acdf21fc9ff1300a4d95be014785f40a730327cb6e481ea8f2319877aef6fb009452b482bd9bd0c6e2e33b9cf23d77ee04bed4df7d038c87b3a935a034b9b73aec300fe51f20320d8cba903517989b9635ecebacff19bace5f6a6d65ed0e90e6f92ed729675b32981ac5499d3c50f7ff5d751d97157ecdeece781a4a24fc3da0353d66972311c8ac836d86f3cd21e2b243f609d32deddc147ea312ea28ce21e2d61d19cea67a2232097333e12e4f3ae89a7545c4c7a3b0329eb24b188eb7b6891b34455e5de9203f92166b59644a2bf7438e56aebf61479e709fd34e2e9ed8957a09e38116142f8727dcb6df16d06b163c3082deb8c3cc721454d19a9e4632269e96d129f5b91a245459332b0fa6b8697d8ac24623a46acdb36aef3a1f28109b5f3c4";
    public String extractUsername(String token) {
        return extractOneClaim(token,Claims::getSubject);
    }
    /*
    *We need to extract all claims in order we can extract username from extracting one of the claims
     */
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte [] keyByte= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
    /**
     * Extracting a single claim from our list of claims in order to extract our username
     */
    public <T>T extractOneClaim(String token, Function<Claims,T>claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String, Object>extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        if (isTokenExpired(token)){
            throw new IllegalStateException("THAT SHIT OF TOKEN IS EXPIRED MAN");
        }
        return(username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractOneClaim(token,Claims::getExpiration);
    }
}

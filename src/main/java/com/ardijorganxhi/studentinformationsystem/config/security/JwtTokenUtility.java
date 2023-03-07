package com.ardijorganxhi.studentinformationsystem.config.security;

import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtility implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }



    public String generateTokenForStudent(Student student){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, student.getUsername());
    }

    public String generateTokenForTeacher(Teacher teacher){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, teacher.getUsername());
    }



    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // validate token


    public Boolean validateTokenForStudent(String token, Student student){

        final String username = getUsernameFromToken(token);
        return (username.equals(student.getUsername()) && !isTokenExpired(token));

    }

    public Boolean validateTokenForTeacher(String token, Teacher teacher){

        final String username = getUsernameFromToken(token);
        return (username.equals(teacher.getUsername()) && !isTokenExpired(token));

    }
}

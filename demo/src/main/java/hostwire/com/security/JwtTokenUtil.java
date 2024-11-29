package hostwire.com.security;

import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;

@Component
public class JwtTokenUtil {   
	private String secret = "asdfg";
	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date(0))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
	                .signWith(SignatureAlgorithm.HS256, secret)
	                .compact();
	    }

	    public String getUsernameFromToken(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	        return claims.getSubject();
	    }

	    public boolean validateToken(String token) {
	        return !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	        return claims.getExpiration().before(new Date(0));
	    }
}

package hostwire.com.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{
	 private JwtTokenUtil jwtTokenUtil;

	    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
	        this.jwtTokenUtil = jwtTokenUtil;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
	            throws ServletException, IOException {
	        String token = request.getHeader("Authorization");
	        if (token != null && token.startsWith("Bearer ")) {
	            token = token.substring(7);
	            String username = jwtTokenUtil.getUsernameFromToken(token);
	            if (username != null && jwtTokenUtil.validateToken(token)) {
	                SecurityContextHolder.getContext().setAuthentication(null); 
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
}

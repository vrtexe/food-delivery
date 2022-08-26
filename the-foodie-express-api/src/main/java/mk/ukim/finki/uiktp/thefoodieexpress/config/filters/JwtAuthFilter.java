package mk.ukim.finki.uiktp.thefoodieexpress.config.filters;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.uiktp.thefoodieexpress.config.JwtAuthConstants;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    private final JWTVerifier verifier;

    public JwtAuthFilter(AuthenticationManager authenticationManager, JWTVerifier verifier) {
        super(authenticationManager);
        this.verifier = verifier;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = !request.getServletPath().isBlank()
                ? request.getServletPath()
                : request.getRequestURI();
        return path.startsWith("/swagger-ui") || // Swagger UI
                path.startsWith("/v3/api-docs") || // Swagger UI source JSON
                path.equals("/api/users/register") ||
                path.equals("/api/users/login");
    
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtAuthConstants.HEADER_STRING);

        if (header == null || !header.startsWith(JwtAuthConstants.TOKEN_PREFIX)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing auth information.");
            return;
        }

        String jwtToken = header.replace(JwtAuthConstants.TOKEN_PREFIX, "");

        try {
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            String subject = decodedJWT.getSubject();

            UserDetails userDetails = new ObjectMapper().readValue(subject, UserDetails.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails.getEmail(), null, Collections.singleton(userDetails.getRole())
            );

            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token.");
        }
    }
}

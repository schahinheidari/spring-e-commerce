/*
package fr.tln.univ.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private static final String HEADER_STRING = "Authorization";
    private SecurityService securityService;

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest httpRequest,
           @NonNull HttpServletResponse httpResponse,
           @NonNull FilterChain filterChain) throws ServletException, IOException {

        //Request don't need to token
        if (httpRequest.getServletPath().contains("/api/v1/auth")) {
            FilterChain.doFilter(httpRequest, httpResponse);
            return;
        }
        final String authHeader = httpRequest.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        //Token invalid
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            FilterChain.doFilter(httpRequest, httpResponse);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.LoadUserByUsername(userEmail);

            var isTokenValid = tokenRepository.findByJit("")
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenvalid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

       */
/* final String token = httpRequest.getHeader(HEADER_STRING);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                securityService.authenticate(token);
            } catch (Exception e) {
                logger.debug("Failed when authenticating token '{}'. Error: '{}'", token, e.getMessage());
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);*//*


}
*/

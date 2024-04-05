package ca.christopher.applicationtache.config;

import ca.christopher.applicationtache.exceptions.AppException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.valideToken(authElements[1]));
                }
                catch (JWTVerificationException e) {
                    SecurityContextHolder.clearContext();
                    throw new AppException("Invalid token", HttpStatusCode.valueOf(401));
                }
                catch (AppException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
                catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw new AppException("Internal error", HttpStatusCode.valueOf(500));
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

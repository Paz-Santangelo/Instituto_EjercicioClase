package com.ProyectoSpring.Instituto.seguridad;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación JWT que intercepta todas las peticiones HTTP
 * Verifica la presencia y validez del token JWT en el header Authorization
 */
@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Procesa cada petición HTTP para verificar la autenticación JWT
     * @param request Petición HTTP entrante
     * @param response Respuesta HTTP
     * @param filterChain Cadena de filtros
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extrae el header Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // 2. Verifica si el header contiene un token Bearer válido
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el token (remueve "Bearer " del header)
        jwtToken = authHeader.substring(7);

        try {
            // 3. Extrae el email del usuario del token
            userEmail = jwtUtils.extractUsername(jwtToken);

            // 4. Si el email existe y no hay autenticación previa en el contexto
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 5. Carga los detalles del usuario desde la base de datos
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // 6. Valida el token contra los detalles del usuario
                if (jwtUtils.isValidToken(jwtToken, userDetails)) {
                    // 7. Crea el contexto de seguridad para el usuario autenticado
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());
                    token.setDetails(userDetails);
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // 8. si el token está expirado, devuelve un error 401 con un mensaje claro
            response.getWriter().write("El Token se ha expirado.");
            return;
        }

        // 9. pasa la petición al siguiente filtro de la cadena
        filterChain.doFilter(request, response);

    }
}

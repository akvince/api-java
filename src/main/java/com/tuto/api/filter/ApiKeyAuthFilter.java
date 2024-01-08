/*
package com.tuto.api.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Récupérer la clé API de l'en-tête de la requête
        String requestApiKey = request.getHeader("X-API-KEY");

        // Vérifier si la clé API est valide
        if (apiKey.equals(requestApiKey)) {
            // Continuer le traitement de la requête si la clé API est valide
            filterChain.doFilter(request, response);
        } else {
            // Rejeter la requête et renvoyer une erreur non autorisée si la clé API n'est pas valide
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized");
        }
    }
}
*/

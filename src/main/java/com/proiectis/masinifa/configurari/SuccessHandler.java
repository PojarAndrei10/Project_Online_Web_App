package com.proiectis.masinifa.configurari;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String redirectURL = "/user"; // Pagina implicita

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        // Obtinem rolurile utilizatorului autentificat
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (hasAdminRole(roles)) {
            redirectURL = "/admin"; // Redirectionam catre pagina de admin pentru utilizatorii cu rolul de admin
        }

        // Redirectionam catre pagina corespunzatoare in functie de rolul utilizatorului
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectURL);
    }

    // Verificam daca lista de roluri contine "ROLE_ADMIN"
    private boolean hasAdminRole(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }
}

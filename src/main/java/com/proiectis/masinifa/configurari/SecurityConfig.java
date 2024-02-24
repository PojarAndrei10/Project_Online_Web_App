package com.proiectis.masinifa.configurari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationSuccessHandler successHandler;

    // Definim un bean pentru a obtine un encoder de parole
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // Configuram filtrul de securitate pentru diferitele cai de acces
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Autorizam accesul la anumite resurse in functie de URL-uri si roluri
        http.authorizeRequests(configurer -> configurer
                .antMatchers("/css/**", "/images/**", "/js/**").permitAll() // Permitem accesul la resurse statice
                .antMatchers("/").permitAll() // Permitem accesul la pagina principala
                .antMatchers("/cars/**").permitAll() // Permitem accesul la pagina de masini
                .antMatchers("/register/**").permitAll() // Permitem accesul la pagina de inregistrare
                .antMatchers("/user/**").hasRole("USER") // Cerem rolul "USER" pentru accesul la anumite resurse
                .antMatchers("/car-bid/**").hasRole("USER") // Cerem rolul "USER" pentru accesul la licitatii de masini
                .antMatchers("/test-drive/**").hasRole("USER") // Cerem rolul "USER" pentru accesul la test-drive-uri
                .antMatchers("/admin/**").hasRole("ADMIN")); // Cerem rolul "ADMIN" pentru accesul la zona de admin

        // Configuram pagina de login
        http.formLogin(form -> form
                .loginPage("/login") // Pagina de login
                .loginProcessingUrl("/loginUser") // URL-ul pentru procesarea cererii de login
                .successHandler(successHandler) // Setam un handler pentru actiunea de login cu succes
                .permitAll()); // Permitem accesul la pagina de login

        // Configuram functionalitatea de logout
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Configuram URL-ul pentru logout
                .permitAll()); // Permitem accesul la functionalitatea de logout

        return http.build(); // Returnam filtrul de securitate configurat
    }
}

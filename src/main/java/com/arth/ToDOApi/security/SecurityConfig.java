package com.arth.ToDOApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{

    @Autowired
    private SecurityFilter securityFilter;
    
    public static final String[] ROTAS_LIVRES =
    {
        "/user/add",
        "/user/login"
    };

    private static final String[] SOMENTE_USUARIOS_COMUNS = 
    {
        "/task/add",
        "/task/{username}"
    };

    private static final String[] SOMENTE_ADMINS = 
    {
        "/user/all",
        "/user/erase/{id}"
    };

    private static final String[] SOMENTE_USUARIOS_AUTENTICADOS =
    {
        "/user/{username}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity.csrf(csrf -> csrf.disable())
        .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, ROTAS_LIVRES).permitAll()
        .requestMatchers(HttpMethod.GET, SOMENTE_USUARIOS_COMUNS[1]).hasRole("USER")
        .requestMatchers(HttpMethod.POST, SOMENTE_USUARIOS_COMUNS[0]).hasRole("USER")
        .requestMatchers(HttpMethod.GET, SOMENTE_ADMINS[0]).hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, SOMENTE_ADMINS[1]).hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, SOMENTE_USUARIOS_AUTENTICADOS[0]).authenticated()
        .anyRequest().denyAll()
        ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

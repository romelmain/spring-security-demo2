package com.app.demo2.config;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
     * throws Exception {
     * 
     * return httpSecurity
     * .csrf(csrf -> csrf.disable())
     * .httpBasic(Customizer.withDefaults())
     * .sessionManagement(session ->
     * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     * .authorizeHttpRequests(
     * http -> {
     * // Configurar los endpoint Publicos
     * http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
     * 
     * // Configurar los endpoint Privados
     * http.requestMatchers(HttpMethod.GET,
     * "/auth/hello-secured").hasAuthority("CREATE");
     * 
     * // Configurar el resto de los endpoint - No especificados
     * // http.anyRequest().authenticated();
     * 
     * // Niega todo lo que no se especifique
     * http.anyRequest().denyAll();
     * })
     * .build();
     * 
     * }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("romel").password("1234").roles("ADMIN")
                .authorities("READ", "CREATE").build());

        userDetailsList.add(User.withUsername("juan").password("1234").roles("ADMIN")
                .authorities("READ").build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}

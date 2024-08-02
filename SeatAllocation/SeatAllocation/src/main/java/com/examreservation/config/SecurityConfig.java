package com.examreservation.config;

//import com.examreservation.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Updated CSRF configuration
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll() // Allow public access to /login
                        .requestMatchers("/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/faculty/**").hasRole("ADMIN")
                        .requestMatchers("/v1/hall/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .permitAll() // Use the default Spring Security login page
                        .defaultSuccessUrl("/v1/admin", true) // Redirect to this URL after successful login
                        .failureUrl("/login?error=true")
                        .loginPage("/login")
                )
                .logout(logout -> logout
                        .logoutUrl("/custom-logout") // Custom logout URL
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("superadmin")
                .password("superadmin")
                .roles("ADMIN")
                .build());
        return manager;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}

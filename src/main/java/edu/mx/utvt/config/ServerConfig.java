package edu.mx.utvt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class ServerConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Security Filter");

        return httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/patients/**").permitAll().requestMatchers(HttpMethod.POST, "/patients/**").hasRole("ADMIN").anyRequest().authenticated()).csrf(csrf -> csrf.disable()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() throws Exception {
        final String prefix = "{bcrypt}";
        final String password = prefix + "$2a$10$MUlAgxy85QMLdRxA4rt5h.BoASRo0dkoahkswI9qGzMJ8knsrcduy";

        UserDetails user = User.builder().username("user").password(password).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(password).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
 }

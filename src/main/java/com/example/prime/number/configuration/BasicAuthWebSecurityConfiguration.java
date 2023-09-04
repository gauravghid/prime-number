package com.example.prime.number.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableMethodSecurity
public class BasicAuthWebSecurityConfiguration
{
    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String password;

    /**
     * method to secures all endpoints with HTTP Basic:
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz)-> authz
                .anyRequest()
                .authenticated()

        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * method to provide support for username/password based authentication that is stored in memory
     * @return InMemoryUserDetailsManager
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername(userName)
                .password(passwordEncoder().encode(password))
                .roles("USER_ROLE")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * method to encode the password
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}

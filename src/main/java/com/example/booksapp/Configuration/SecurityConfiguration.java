package com.example.booksapp.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final UserDetailsService userDetailsService;


    @Autowired
    protected SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

    }

    @Bean
   protected AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());

        return provider;

    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/t")
                .hasAuthority("USER")
                .antMatchers(HttpMethod.POST,"/users")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/users/**")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
@Bean
   protected PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
}

}
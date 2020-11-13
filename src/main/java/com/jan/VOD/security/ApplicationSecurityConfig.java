package com.jan.VOD.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.jan.VOD.security.ApplicationUserPermission.*;
import static com.jan.VOD.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(NORMALUSER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses",true)
                .and().rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(365)).key("secured")
                .and().logout().logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login");

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails janMarchelUser = User.builder()
                .username("janmarchel")
                .password(passwordEncoder.encode("haslo"))
//                .roles(NORMALUSER.name())
                .authorities(ADMIN.grantedAuthorities())
                .build();

        UserDetails adamUser = User.builder()
                .username("adam")
                .password(passwordEncoder.encode("haslo123"))
                .authorities(NORMALUSER.grantedAuthorities())
                .build();
        UserDetails lukaszUser = User.builder()
                .username("lukasz")
                .password(passwordEncoder.encode("haslo123"))
                .authorities(ADMINTRAINEE.grantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                janMarchelUser,
                adamUser,
                lukaszUser
        );
    }
}

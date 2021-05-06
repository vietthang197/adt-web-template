package com.adt.adtwebtemplate.config;

import com.adt.adtwebtemplate.services.impl.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Locale;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServicesImpl userDetailsServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(259200);
        http.csrf().and()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionConcurrency(concurrencyControlConfigurer -> {
            concurrencyControlConfigurer.maximumSessions(1);
            concurrencyControlConfigurer.maxSessionsPreventsLogin(false);
            concurrencyControlConfigurer.expiredUrl("/");
        }).and().
                authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check").loginPage("/login").usernameParameter("username").passwordParameter("password").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .addLogoutHandler(new HeaderWriterLogoutHandler(
                        new ClearSiteDataHeaderWriter(
                                ClearSiteDataHeaderWriter.Directive.ALL)))
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/404");
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/public/**");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization,Link,X-Total-Count,Content-Disposition,Expires");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(1800L);
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServices).passwordEncoder(passwordEncoder());
    }
}

package com.emanet.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * Spring Security Configuration http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
 * Switches off Spring Boot automatic security configuration
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final String USER = "USER";

    private final String ADMIN = "ADMIN";

    final DataSource dataSource;

    @Value("${spring.admin.username}")
    private String adminUsername;

    @Value("${spring.admin.username}")
    private String adminPassword;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public SpringSecurityConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
    }

    // HTTPSecurity configured
    // ADMIN role allowed to access /admin/** /newPost/** /editPost/**
    // USER role allowed to access /newCommentPost/** /editCommentPost/** /editCommentRmzlhds/** /newCommentRmzlhds/** /postComment/** /rmzlhdsComment/**
    // anybody can visit / /error /registration /login /home /blog/** /post/** /rmzlhds/** /rmzlhds /searchHadis
    // every other page needs authentication
    // custom 403 access denied handler
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
            .antMatchers("/home", "/rmzlhds", "/searchHadis", "/registration", "/error", "/blog/**", "/post/**", "/rmzlhds/**").permitAll()
            .antMatchers("/newCommentPost/**", "/editCommentPost/**", "/editCommentRmzlhds/**", "/newCommentRmzlhds/**", "/postComment/**", "/rmzlhdsComment/**").hasAnyRole(USER)
            .antMatchers("/newPost/**", "/editPost/**").hasAnyRole(ADMIN).anyRequest().authenticated().and().formLogin()
            .loginPage("/login").defaultSuccessUrl("/home").permitAll().and().logout().permitAll().and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/images/**");
    }

    /**
     * Authentication details
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Database authentication
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource).passwordEncoder(passwordEncoder());

        // In memory authentication
        auth.inMemoryAuthentication().withUser(adminUsername).password(adminPassword).roles(ADMIN);
    }

    /**
     * Configure and return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

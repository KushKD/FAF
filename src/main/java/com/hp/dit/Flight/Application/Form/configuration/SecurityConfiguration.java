package com.hp.dit.Flight.Application.Form.configuration;

import com.hp.dit.Flight.Application.Form.captchasecurity.CaptchaAuthenticationProvider;
import com.hp.dit.Flight.Application.Form.captchasecurity.CaptchaDetailsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CaptchaAuthenticationProvider authenticationProvider;

    @Autowired
    private CaptchaDetailsSource detailsSource;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        http.csrf()
                .csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
        http.csrf().ignoringAntMatchers("/nocsrf", "/paymentResponse/**");

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/downloadFile/**").permitAll()
                .antMatchers("/gallery/**").permitAll()
                .antMatchers("/contactus/**").permitAll()
                .antMatchers("/paymentpage/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("Admin")
                .antMatchers("/createuser/**").hasAnyRole("Admin")
                .antMatchers("/saveuser/").hasAnyRole("Admin")
                .antMatchers("/createrole/").hasAnyRole("Admin")
                .antMatchers("/filterApplications/").hasAnyRole("Admin")
                .antMatchers("/applications/").hasAnyRole("Admin")
                .antMatchers("/getUserDetails/**").hasAnyRole("Admin")
                .antMatchers("/updateApplication/**").hasAnyRole("Admin")
                .antMatchers("/applications_all/**").hasAnyRole("Admin")
                .antMatchers("/checkpayment/**").hasAnyRole("Admin")
                .antMatchers("/index/**").hasAnyRole("Admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .authenticationDetailsSource(detailsSource).permitAll().and()
                .logout().logoutSuccessHandler(logoutSuccessHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .and()
                .invalidSessionUrl("/login");


    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        cookie.setSecure(true);
                        cookie.setHttpOnly(true);
                        response.addCookie(cookie);
                    }
                }
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("pragma", "no-cache");
                response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
              //  response.setHeader("Set-Cookie", "locale=de;  SameSite=same-origin");  //HttpOnly;
                 filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");


        return repository;
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response
                .sendRedirect("/index");
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("error", "Bad Credentials");
            response.sendRedirect( "/login");
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            request.getSession().setAttribute("message", "Logout Successful.");
            response.sendRedirect("/login");
        };
    }
}

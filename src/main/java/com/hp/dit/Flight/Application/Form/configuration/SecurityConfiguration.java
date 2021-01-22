package com.hp.dit.Flight.Application.Form.configuration;

import com.hp.dit.Flight.Application.Form.captchasecurity.CaptchaAuthenticationProvider;
import com.hp.dit.Flight.Application.Form.captchasecurity.CaptchaDetailsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import org.springframework.core.env.Environment;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CaptchaAuthenticationProvider authenticationProvider;

    @Autowired
    private CaptchaDetailsSource detailsSource;


//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

//    @Qualifier("userDetailsServiceImpl")
//    @Autowired
//    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);


//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Autowired  // configure //@Override
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
         // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
        // http.csrf().disable();
        http.csrf()
                .csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
        http.csrf().ignoringAntMatchers("/nocsrf", "/paymentResponse/**");
      //  http.csrf().ignoringAntMatchers("/nocsrf", "/ajax/**");
        
         //.anonymous()
          //.and()
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/downloadFile/**").permitAll()
                .antMatchers("/gallery/**").permitAll()
                .antMatchers("/contactus/**").permitAll()
                .antMatchers("/paymentpage/**").permitAll()
                //.antMatchers("/paymentResponse/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                //.antMatchers("/ajax/**").denyAll()
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
               // .defaultSuccessUrl("/index")
               // .permitAll()
               // .and()
                //.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.logoutSuccessUrl("/login")
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

    @Autowired
    private Environment environment;

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

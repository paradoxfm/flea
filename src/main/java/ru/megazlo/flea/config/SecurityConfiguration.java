package ru.megazlo.flea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author iv - 23.01.2016
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan({"ru.megazlo.flea.services.impl"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // время запоминания пользователя (год)
    private static final int REMEMBER_ME_TIME_YEAR = 31_536_000;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private DataSource dataSource;

    @Value("${application.use.ssl}")
    private boolean useSsl;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/fonts/**", "/t/**", "/appjs/**", "/css/**", "/js/**", "/img/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/intro**", "/login/**", "/user/**").permitAll()
                //.antMatchers("/intro**", "/login/**", "/user/**", "/fonts/**", "/t/**", "/appjs/**", "/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()

                .and().formLogin().loginPage("/intro").loginProcessingUrl("/auth/login_check")//.defaultSuccessUrl("/", false).failureUrl("/intro?error")
                .failureHandler((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "login failure"))//эта приколюха для anguarjs
                .usernameParameter("username").passwordParameter("password")

                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/intro?logout").deleteCookies("myid", "JSESSIONID", "remember-me").invalidateHttpSession(true)

                .and().rememberMe().rememberMeParameter("rememberMe").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(REMEMBER_ME_TIME_YEAR)
                //.and().apply(new SpringSocialConfigurer())
                .and().csrf().disable();

        if (useSsl) {
            http.requiresChannel().anyRequest().requiresSecure();
        }
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        /*if (GlobalUtil.isDebug()) {
            return new InMemoryTokenRepositoryImpl();
        }*/
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }
}

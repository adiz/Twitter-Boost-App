package ro.cti.ssa.twittboost.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ro.cti.ssa.twittboost.service.AuthenticationFailureHandlerService;
import ro.cti.ssa.twittboost.service.TwitterUserDetailsService;

/**
 * @author adrian.zamfirescu
 * @since 17/01/2015
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TwitterUserDetailsService twitterUserDetailsService;
    @Autowired
    private AuthenticationFailureHandlerService authenticationFailureHandlerService;

    @Autowired
    public void configureSecurityApp(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(twitterUserDetailsService);

    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureHandler(authenticationFailureHandlerService)
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
            .csrf()
                .disable();
    }

}

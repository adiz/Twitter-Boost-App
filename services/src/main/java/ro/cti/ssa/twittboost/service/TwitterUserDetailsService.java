package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.service.AppUserService;

import java.util.Arrays;

/**
* @author adrian.zamfirescu
* @since 18/01/2015
*/
@Component
public class TwitterUserDetailsService implements UserDetailsService{

    @Autowired
    private AppUserService appUserService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser logingUser = appUserService.getUserByUsername(username);
        return new User(logingUser.getUsername(),
                        logingUser.getPassword(),
                        Arrays.asList(new SimpleGrantedAuthority(logingUser.getRole())));

    }

}

package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dao.AppUserRepository;
import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.framework.IAppUserService;
import ro.cti.ssa.twittboost.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser getUserByUsername(String username){

        return appUserRepository.findByUsername(username);

    }

    public boolean registerAppUser(AppUser appUser) throws AppUserCreationException {

        AppUser existingUser = appUserRepository.findByUsername(appUser.getUsername());
        if (existingUser!=null)
            throw new AppUserCreationException("Error: User already exists!");

        appUser.setRole("user");
        AppUser newUser = appUserRepository.save(appUser);
        if (newUser!=null)
            return true;
        else
            throw new AppUserCreationException("User could not be created! Please retry.");

    }

}

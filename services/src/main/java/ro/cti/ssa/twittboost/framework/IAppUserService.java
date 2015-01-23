package ro.cti.ssa.twittboost.framework;

import ro.cti.ssa.twittboost.exception.AppUserCreationException;
import ro.cti.ssa.twittboost.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public interface IAppUserService {

    AppUser getUserByUsername(String username);

    boolean registerAppUser(AppUser appUser) throws AppUserCreationException;

}

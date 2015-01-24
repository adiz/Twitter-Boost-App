package ro.cti.ssa.twittboost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.cti.ssa.twittboost.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query(value = "SELECT user from AppUser user where user.username = ?1")
    AppUser getUserByName(String userName);

    @Query
    AppUser findByUsername(String username);

}

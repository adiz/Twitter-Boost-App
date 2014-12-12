package ro.cti.ssa.twittboost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.cti.ssa.twittboost.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer>{
}

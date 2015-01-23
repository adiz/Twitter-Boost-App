package ro.cti.ssa.twittboost.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;

import java.util.List;

/**
 * Created by livia on 1/22/2015.
 */
@Repository
public interface FilterGroupRepository extends JpaRepository<FilterGroupPreference, Integer> {


    @Query("SELECT filterGroup FROM FilterGroupPreference filterGroup WHERE filterGroup.user = ?1")
    List<FilterGroupPreference> getFilterGroupForUser(AppUser user);
}

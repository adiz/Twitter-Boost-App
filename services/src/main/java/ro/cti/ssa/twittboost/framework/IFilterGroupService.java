package ro.cti.ssa.twittboost.framework;

import ro.cti.ssa.twittboost.dto.SaveFilterGroupForm;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;

import java.util.List;

/**
 * Created by livia on 1/22/2015.
 */
public interface IFilterGroupService {

    public void saveFilterGroup(SaveFilterGroupForm filterGroupFormData);

    public List<FilterGroupPreference> getFilterGroupsForUser(AppUser user);

    public FilterGroupPreference getFilterGroupById(Integer filterGroupId);

    public void deleteFilterGroup(Integer filterGroupId);

}

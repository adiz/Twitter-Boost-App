package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dao.FilterGroupRepository;
import ro.cti.ssa.twittboost.dto.Filters;
import ro.cti.ssa.twittboost.dto.SaveFilterGroupForm;
import ro.cti.ssa.twittboost.framework.IFilterGroupService;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.SavedFilter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by livia on 1/22/2015.
 */
@Service
public class FilterGroupService implements IFilterGroupService {

    @Autowired
    private FilterGroupRepository filterGroupRepository;

    @Autowired
    private AppUserService appUserService;

    @Override
    public void saveFilterGroup(SaveFilterGroupForm filterGroupFormData) {
        AppUser user = appUserService.getUserByName(filterGroupFormData.getUser());
        FilterGroupPreference filterGroupPreference = new FilterGroupPreference();
        filterGroupPreference.setFilterGroupDescription(filterGroupFormData.getFilterGroupDescription());
        filterGroupPreference.setUser(user);

        List<String> filters = filterGroupFormData.getFilters();
        Set<SavedFilter> savedFilters = filterGroupPreference.getFilters();
        for (int index = 0; index < filters.size(); index++) {
            String filterContent = filters.get(index);
            if (!filterContent.isEmpty()) {
                SavedFilter savedFilter = new SavedFilter(Filters.getFilterByIndex(index).toString(), filterContent);
                savedFilter.setFilterGroup(filterGroupPreference);
                savedFilters.add(savedFilter);
            }
        }
        if (!filterGroupFormData.getUsername().isEmpty()) {
            SavedFilter savedFilter = new SavedFilter(Filters.USER_NAME.toString(), filterGroupFormData.getUsername());
            savedFilter.setFilterGroup(filterGroupPreference);
            savedFilters.add(savedFilter);
        }

        filterGroupRepository.save(filterGroupPreference);
    }

    @Override
    public List<FilterGroupPreference> getFilterGroupsForUser(AppUser user) {
        return filterGroupRepository.getFilterGroupForUser(user);
    }

    @Override
    public FilterGroupPreference getFilterGroupById(Integer filterGroupId) {
        return filterGroupRepository.findOne(filterGroupId);
    }

    @Override
    public void deleteFilterGroup(Integer filterGroupId) {
        filterGroupRepository.delete(filterGroupId);
    }


}

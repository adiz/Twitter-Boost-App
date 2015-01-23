package ro.cti.ssa.twittboost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.cti.ssa.twittboost.dto.SaveFilterGroupForm;
import ro.cti.ssa.twittboost.model.AppUser;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.service.AppUserService;
import ro.cti.ssa.twittboost.service.FilterGroupService;

import java.util.List;

/**
 * Created by livia on 1/22/2015.
 */
@Controller
@RequestMapping("filterGroups")
public class FilterGroupController {

    @Autowired
    private FilterGroupService filterGroupService;

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/saveFilterGroup", method = RequestMethod.POST)
    @ResponseBody
    public void saveFilterGroup(@RequestBody SaveFilterGroupForm saveFilterGroupForm) {
        filterGroupService.saveFilterGroup(saveFilterGroupForm);
    }

    @RequestMapping(value = "/delete/{filterGroupId}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteFilterGroup(@PathVariable Integer filterGroupId) {
        filterGroupService.deleteFilterGroup(filterGroupId);
    }

    @RequestMapping(value = "/getAllForUser/{username}", method = RequestMethod.POST)
    @ResponseBody
    public List<FilterGroupPreference> getFilterGroupsForUser(@PathVariable String username) {
        AppUser user = appUserService.getUserByName(username);
        return filterGroupService.getFilterGroupsForUser(user);
    }


}

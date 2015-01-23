package ro.cti.ssa.twittboost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.framework.ITwitterService;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.Tweet;
import ro.cti.ssa.twittboost.service.FilterGroupService;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 08/12/2014
 */
@Controller
@RequestMapping("twitter")
public class TwitterController {

    @Autowired
    private ITwitterService twitterService;

    @Autowired
    private FilterGroupService filterGroupService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> getAllTweets() {
        return twitterService.getAllTweets();
    }

    @RequestMapping(value = "/searchByFormFilters", method = RequestMethod.POST)
    @ResponseBody
    public List<Tweet> getSearchedTweets(@RequestBody SearchForm searchForm) {
        return twitterService.getSearchedTweetsInForm(searchForm);
    }

    @RequestMapping(value = "/searchByFilterGroup/{filterGroupId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Tweet> getTweetsForSavedFilters(@PathVariable Integer filterGroupId) {
        FilterGroupPreference filterGroupPreference = filterGroupService.getFilterGroupById(filterGroupId);
        return twitterService.getTweetsForSavedFilters(filterGroupPreference);
    }

}

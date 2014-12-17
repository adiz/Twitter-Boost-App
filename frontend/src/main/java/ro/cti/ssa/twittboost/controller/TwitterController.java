package ro.cti.ssa.twittboost.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.framework.ITwitterService;
import ro.cti.ssa.twittboost.model.Tweet;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> getAllTweets(){
        return twitterService.getAllTweets();
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<Tweet> getSearchedTweets(@RequestBody SearchForm searchForm){
        return twitterService.getSearchedTweets(searchForm);
    }

}

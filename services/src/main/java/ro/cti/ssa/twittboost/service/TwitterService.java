package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dao.TweetRepository;
import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.framework.ITweetSearchService;
import ro.cti.ssa.twittboost.framework.ITwitterService;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.Tweet;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class TwitterService implements ITwitterService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ITweetSearchService tweetSearchService;

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public List<Tweet> getSearchedTweetsInForm(SearchForm searchForm) {
        return tweetSearchService.searchByFormFilters(searchForm);
    }

    @Override
    public List<Tweet> getTweetsForSavedFilters(FilterGroupPreference filterGroupPreference) {
        return tweetSearchService.searchBySavedFilters(filterGroupPreference);
    }


}

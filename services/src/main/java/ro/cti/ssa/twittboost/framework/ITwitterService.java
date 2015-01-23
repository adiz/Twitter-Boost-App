package ro.cti.ssa.twittboost.framework;

import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.Tweet;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public interface ITwitterService {

    List<Tweet> getAllTweets();

    List<Tweet> getSearchedTweetsInForm(SearchForm searchForm);

    List<Tweet> getTweetsForSavedFilters(FilterGroupPreference filterGroupPreference);
}

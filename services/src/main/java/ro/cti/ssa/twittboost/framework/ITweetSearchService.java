package ro.cti.ssa.twittboost.framework;

import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.Tweet;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public interface ITweetSearchService {

    List<Tweet> searchByFormFilters(SearchForm searchForm);

    List<Tweet> searchBySavedFilters(FilterGroupPreference filterGroupPreference);
}

package ro.cti.ssa.twittboost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.framework.ISentimentAnalysisService;
import ro.cti.ssa.twittboost.framework.ITweetSearchService;
import ro.cti.ssa.twittboost.model.FilterGroupPreference;
import ro.cti.ssa.twittboost.model.SavedFilter;
import ro.cti.ssa.twittboost.model.Tweet;
import twitter4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class TweetSearchService implements ITweetSearchService {

    private static final String USERNAME = "from:";
    private static final String REFERENCES = "%40";
    private static final String HASHTAGS = "%23";
    private static final String SPACE = "%20";

    private static Twitter twitter = new TwitterFactory().getInstance();

    @Autowired
    private ISentimentAnalysisService sentimentAnalysisService;

    @Override
    public List<Tweet> searchBySavedFilters(FilterGroupPreference filterGroupPreference) {
        List<Tweet> searchResults = new ArrayList<Tweet>();
        String twitterQuery = buildSearchQueryBySavedFilterGroup(filterGroupPreference);
        searchResults = search(twitterQuery);
        return searchResults;
    }

    public List<Tweet> searchByFormFilters(SearchForm searchForm) {
        List<Tweet> searchResults = new ArrayList<Tweet>();
        String twitterQuery = buildSearchQueryBySearchForm(searchForm);
        searchResults = search(twitterQuery);
        return searchResults;
    }

    public List<Tweet> search(String twitterQuery) {

        List<String> tweetsTexts = new ArrayList<String>();

        List<Tweet> searchResults = new ArrayList<Tweet>();

        try {

            Query query = new Query(twitterQuery);
            QueryResult result;

            do {

                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                System.out.println("NO TWEETS: " + tweets.size());

                for (Status tweet : tweets) {

                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    tweetsTexts.add(tweet.getText());

                    Tweet newTweet = new Tweet(tweet.getUser().getScreenName());
                    newTweet.setContent(tweet.getText());
                    newTweet.setDate(tweet.getCreatedAt().toString());

                    try {
                        String sentimentAnalysisResult = sentimentAnalysisService.extractSentiment(tweet.getText());
                        newTweet.setCategories(sentimentAnalysisResult);
                    } catch (IOException e) {
                        newTweet.setCategories("undefined");
                    }

                    searchResults.add(newTweet);

                }

            } while ((query = result.nextQuery()) != null && searchResults.size() < 30);

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return searchResults;

    }

    private String buildSearchQueryForFilterGroup(FilterGroupPreference filterGroupPreference) {
        return "";
    }

    private String buildSearchQueryBySavedFilterGroup(FilterGroupPreference filterGroupPreference) {
        StringBuilder queryBuilder = new StringBuilder();
        for (SavedFilter filter : filterGroupPreference.getFilters()) {
            if (filter.isWordsFilter()) {
                // special case: words are first in the searchByFormFilters query
                queryBuilder.append(buildSearchWords(filter.getFilterContent()));
                break;
            }
        }

        for (SavedFilter filter : filterGroupPreference.getFilters()) {
            if (filter.isTwitterUserFilter()) {
                // special case: words are first in the searchByFormFilters query
                queryBuilder.append(SPACE).append(USERNAME).append(filter.getFilterContent());
                break;
            }
        }

        for (SavedFilter filter : filterGroupPreference.getFilters()) {
            if (filter.isHashTagFilter()) {
                queryBuilder.append(SPACE).append(HASHTAGS).append(filter.getFilterContent());
            } else if (filter.isRefferenceFilter()) {
                queryBuilder.append(SPACE).append(REFERENCES).append(filter.getFilterContent());
            }
        }

        return queryBuilder.toString().replaceAll(" ", SPACE);
    }

    private String buildSearchQueryBySearchForm(SearchForm searchForm) {

        StringBuilder queryBuilder = new StringBuilder();

        // special case: words are first in the searchByFormFilters query
        if (!searchForm.getFilters().get(2).isEmpty())
            queryBuilder.append(buildSearchWords(searchForm.getFilters().get(2)));

        if (!searchForm.getUsername().isEmpty())
            queryBuilder.append(SPACE).append(USERNAME).append(searchForm.getUsername());

        for (int filterIndex = 0; filterIndex < searchForm.getFilters().size(); filterIndex++) {
            String filter = searchForm.getFilters().get(filterIndex);
            if (!filter.isEmpty()) {
                switch (filterIndex) {
                    case 0:
                        queryBuilder.append(SPACE).append(HASHTAGS).append(filter);
                        break;
                    case 1:
                        queryBuilder.append(SPACE).append(REFERENCES).append(filter);
                        break;
                }
            }
        }

        return queryBuilder.toString().replaceAll(" ", SPACE);
    }

    private String buildSearchWords(String wordsString) {

        String[] words = wordsString.split(" ");
        if (words.length == 1)
            return words[0];

        StringBuilder wordsBuilder = new StringBuilder();
        for (int index = 0; index < words.length - 1; index++)
            wordsBuilder.append(words[index]).append(" OR ");
        wordsBuilder.append(words[words.length - 1]);

        return wordsBuilder.toString();
    }

    public static void main(String[] args) {
        // test
        SearchForm sf = new SearchForm();
        sf.setUsername("lift_behappy");
        List<String> filters = new ArrayList<String>();
        filters.add("");
        filters.add("zinera");
        filters.add("");
        sf.setFilters(filters);
        new TweetSearchService().searchByFormFilters(sf);
    }

}

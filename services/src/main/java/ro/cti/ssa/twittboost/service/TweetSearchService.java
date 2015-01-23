package ro.cti.ssa.twittboost.service;

import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.dto.Filters;
import ro.cti.ssa.twittboost.dto.SearchForm;
import ro.cti.ssa.twittboost.framework.ITweetSearchService;
import ro.cti.ssa.twittboost.model.Tweet;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Service
public class TweetSearchService implements ITweetSearchService{

    private static final String USERNAME = "from:";
    private static final String REFERENCES = "%40";
    private static final String HASHTAGS = "%23";
    private static final String SPACE = "%20";

    private static Twitter twitter = new TwitterFactory().getInstance();

    public List<Tweet> search(SearchForm searchForm) {

        List<String> tweetsTexts = new ArrayList<String>();

        List<Tweet> searchResults = new ArrayList<Tweet>();

        String twitterQuery = buildSearchQuery(searchForm);

        try {

            Query query = new Query(twitterQuery);
            QueryResult result;

            do {

                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                System.out.println("NO TWEETS: "+tweets.size());

                for (Status tweet : tweets) {

                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    tweetsTexts.add(tweet.getText());

                    Tweet newTweet = new Tweet(tweet.getUser().getScreenName());
                    newTweet.setContent(tweet.getText());
                    newTweet.setDate(tweet.getCreatedAt().toString());
                    searchResults.add(newTweet);

                }

            } while ((query = result.nextQuery()) != null && searchResults.size()<30);

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return searchResults;

    }

    private String buildSearchQuery(SearchForm searchForm){

        StringBuilder queryBuilder = new StringBuilder();

        // special case: words are first in the search query
        if (!searchForm.getFilters().get(2).isEmpty())
            queryBuilder.append(buildSearchWords(searchForm.getFilters().get(2)));

        if (!searchForm.getUsername().isEmpty())
            queryBuilder.append(SPACE).append(USERNAME).append(searchForm.getUsername());

        for (int filterIndex=0; filterIndex<searchForm.getFilters().size(); filterIndex++){
            String filter = searchForm.getFilters().get(filterIndex);
            if (!filter.isEmpty()){
                switch (filterIndex){
                    case 0 : queryBuilder.append(SPACE).append(HASHTAGS).append(filter); break;
                    case 1 : queryBuilder.append(SPACE).append(REFERENCES).append(filter); break;
                }
            }
        }

        return queryBuilder.toString().replaceAll(" ",SPACE);
    }

    private String buildSearchWords(String wordsString){

        String[] words = wordsString.split(" ");
        if (words.length==1)
            return words[0];

        StringBuilder wordsBuilder = new StringBuilder();
        for (int index=0; index<words.length-1; index++)
            wordsBuilder.append(words[index]).append(" OR ");
        wordsBuilder.append(words[words.length-1]);

        return wordsBuilder.toString();
    }

    public static void main(String[] args){
        // test
        SearchForm sf = new SearchForm();
        sf.setUsername("lift_behappy");
        List<String> filters = new ArrayList<String>();
        filters.add("");
        filters.add("zinera");
        filters.add("");
        sf.setFilters(filters);
        new TweetSearchService().search(sf);
    }

}

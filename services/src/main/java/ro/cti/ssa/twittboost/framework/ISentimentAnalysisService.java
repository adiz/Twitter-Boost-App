package ro.cti.ssa.twittboost.framework;

import java.io.IOException;

/**
 * Created by livia on 12/16/2014.
 */
public interface ISentimentAnalysisService {
    public String extractSentiment(String tweetText) throws IOException;
}

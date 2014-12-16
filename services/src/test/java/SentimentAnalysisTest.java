import junit.framework.TestCase;
import org.fest.assertions.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ro.cti.ssa.twittboost.config.DaoConfig;
import ro.cti.ssa.twittboost.config.MvcConfig4Test;
import ro.cti.ssa.twittboost.config.ServiceConfig;
import ro.cti.ssa.twittboost.config.TwitterBoostWebAppInitializer;
import ro.cti.ssa.twittboost.service.SentimentAnalysisService;

import java.io.IOException;

/**
 * Created by livia on 12/16/2014.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {MvcConfig4Test.class, DaoConfig.class, ServiceConfig.class, TwitterBoostWebAppInitializer.class})
public class SentimentAnalysisTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Test
    public void testNegativeSentimentAnalysis() {
        String negativeTweetText = "I wish you all to die right now";
        String analysisResult = null;
        try {
            analysisResult = sentimentAnalysisService.extractSentiment(negativeTweetText);
        } catch (IOException e) {
            Fail.fail();
        }
        TestCase.assertEquals("negative", analysisResult);
    }

    @Test
    public void testPositiveSentimentAnalysis() {
        String positiveTweetText = "I feel very happy!!";
        String analysisResult = null;
        try {
            analysisResult = sentimentAnalysisService.extractSentiment(positiveTweetText);
        } catch (IOException e) {
            Fail.fail();
        }
        TestCase.assertEquals("positive", analysisResult);
    }

    @Test
    public void testNeutralSentimentAnalysis() {
        String neutralTweetText = "random";
        String analysisResult = null;
        try {
            analysisResult = sentimentAnalysisService.extractSentiment(neutralTweetText);
        } catch (IOException e) {
            Fail.fail();
        }
        //TestCase.assertEquals("neutral", analysisResult);
    }

}

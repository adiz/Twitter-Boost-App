package ro.cti.ssa.twittboost.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import ro.cti.ssa.twittboost.framework.ISentimentAnalysisService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by livia on 12/16/2014.
 */
@Service
public class SentimentAnalysisService implements ISentimentAnalysisService {

    private final String DATUMBOX_API_KEY = "30ea0da66e6311b338464c2b10241e8c";
    private final String DATUMBOX_REQUEST_URL = "http://api.datumbox.com/1.0/TwitterSentimentAnalysis.json";

    @Override
    public String extractSentiment(String tweetText) throws IOException {

        String urlParameters = "api_key=" + DATUMBOX_API_KEY + "&text=" + tweetText;
        String request = DATUMBOX_REQUEST_URL;
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
        connection.setUseCaches(false);

        DataOutputStream wr = null;

        try {
            wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
        } finally {
            wr.close();
        }
        InputStream is = connection.getInputStream();

        String jsonTxt = IOUtils.toString(is);
        String tweetAnalysysResult = parse(jsonTxt).replace("\"", "");
        return tweetAnalysysResult;

    }

    private String parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("output");
        String result = jobject.get("result").toString();
        return result;
    }
}

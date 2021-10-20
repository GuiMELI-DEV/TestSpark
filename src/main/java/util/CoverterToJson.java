package util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ResponseTransformer;


public class CoverterToJson implements ResponseTransformer {
    Gson gson = new Gson();

    Logger logger = LoggerFactory.getLogger(CoverterToJson.class);

    @Override
    public String render(Object model) throws Exception {
        String jsonOutput = gson.toJson(model);
        logger.info("JSON OUTPUT TO BE SENT = " + jsonOutput);
        return jsonOutput;
    }
}

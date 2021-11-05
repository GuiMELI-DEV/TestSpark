package api_responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ApiResponse {

    // If there is no / in the start of the path, it will be relative to the project's path
    private static final String BASE_PATH = "src/test/groovy/api_responses/jsons";
    protected static final ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static String getFile(String fileName) {
        File file = new File(BASE_PATH + fileName + ".json");
        try {
            return FileUtils.readFileToString(file, ENCODING.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    protected static String serialize(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}

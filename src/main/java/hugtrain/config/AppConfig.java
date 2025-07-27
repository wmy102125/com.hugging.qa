package hugtrain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${api_key}")
    private String apiKey;


    public String getApiKey() {
       return apiKey;
    }

}

package hugtrain.api;

import hugtrain.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class HuggingFaceAPI {
    private static final String API_URL = "https://api-inference.huggingface.co/models/bert-base-chinese";
    private static String API_KEY;
    @Autowired
    private AppConfig appConfig;

//    static {
//        try {
////            API_KEY = Files.readString(Paths.get("src/main/resources/application.properties"))
////                    .split("=")[1].trim();
//            API_KEY=appConfig.getApiKey();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load API key", e);
//        }
//    }

    /**
     * 远程API调用
     * @param inputText
     * @return
     * @throws Exception
     */
    public  String query(String inputText) throws Exception {
        String jsonInput = String.format("{\"inputs\": \"%s\"}", inputText);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + appConfig.getApiKey())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("API Error: " + response.body());
        }
    }

    public static void main(String[] args) throws Exception {
        String input = Files.readString(Paths.get("data/test_input.txt"));

        // 方式1：调用远程API
        System.out.println("=== API 调用结果 ===");
        System.out.println(new HuggingFaceAPI().query(input));
    }
}
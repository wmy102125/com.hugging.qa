package demo.client;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.json.*;

public class HuggingFaceChatClient {
    private static final String API_URL = "http://localhost:5000/chat";

    public static String getChatResponse(String message) throws IOException {
        // 创建JSON请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("message", message);

        // 设置HTTP连接
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // 发送请求
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 读取响应
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // 解析JSON响应
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("response");
        }
    }

    public static void main(String[] args) {
        try {
            String response = getChatResponse("I'm stressed recently");
            System.out.println("AI: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

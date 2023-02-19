package xyz.chaofan.web;

import cn.hutool.json.JSONObject;
import xyz.chaofan.entity.request.GPTRequestInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiAcessor {

    public static BufferedReader requestCompletions(GPTRequestInfo requestInfo) throws Exception{
        String authorization = "Bearer " + requestInfo.getApiKey();
        URL url = new URL("https://api.openai.com/v1/completions");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", authorization);
        conn.setDoOutput(true);


        JSONObject requestBody = new JSONObject();
        requestBody.set("model",requestInfo.getModel());
        requestBody.set("prompt", requestInfo.getMessage());
        requestBody.set("max_tokens", requestInfo.getMaxToken());
        requestBody.set("stream",requestInfo.getStream());

        conn.getOutputStream().write(requestBody.toString().getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return reader;
    }
}

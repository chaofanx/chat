package xyz.chaofan.entity.request;

import lombok.Data;

@Data
public class GPTRequestInfo {
    public static String Davinci003 = "text-davinci-003";
    public static String Ada = "ada";

    public GPTRequestInfo(String message, String apiKey){
        this.message = message;
        this.apiKey = apiKey;
    }
    public GPTRequestInfo(String message, String apiKey, Boolean stream){
        this.message = message;
        this.apiKey = apiKey;
        this.stream = stream;
    }

    String apiKey;
    String model = Davinci003;
    Boolean stream = false;
    String message;
    Integer maxToken = 200;
}

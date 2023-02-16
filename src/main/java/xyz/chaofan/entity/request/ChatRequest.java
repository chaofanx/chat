package xyz.chaofan.entity.request;

import lombok.Data;

@Data
public class ChatRequest {
  private String message;
  private String openid;
}

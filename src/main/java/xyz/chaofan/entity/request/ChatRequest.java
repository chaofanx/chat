package xyz.chaofan.entity.request;

import lombok.Data;
import xyz.chaofan.entity.User;

@Data
public class ChatRequest {
  private String message;
  private User user;
}

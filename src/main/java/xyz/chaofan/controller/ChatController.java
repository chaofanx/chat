package xyz.chaofan.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import xyz.chaofan.entity.request.ChatRequest;
import xyz.chaofan.entity.response.Wrapper;
import xyz.chaofan.service.ChatService;

@Controller
@Mapping("/chat")
public class ChatController {

  @Inject
  private ChatService chatService;

  @Post
  @Mapping("/complete")
  public Wrapper complete(ChatRequest request) {
    return Wrapper.success(chatService.complete(request.getMessage(), request.getOpenid()));
  }
}

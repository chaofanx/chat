package xyz.chaofan.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import xyz.chaofan.entity.request.ChatRequest;
import xyz.chaofan.service.ChatService;

@Controller
@Mapping("/chat")
public class ChatController {

  @Inject
  private ChatService chatService;

  @Post
  @Mapping("/complete")
  public SaResult complete(ChatRequest request) {
    StpUtil.checkLogin();
    return SaResult.ok(chatService.complete(request.getMessage(), request.getOpenid()));
  }
}

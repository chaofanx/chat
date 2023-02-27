package xyz.chaofan.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Delete;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import xyz.chaofan.entity.User;
import xyz.chaofan.entity.request.ChatRequest;
import xyz.chaofan.entity.request.GPTRequestInfo;
import xyz.chaofan.service.APIKeyService;
import xyz.chaofan.service.ChatService;

@Controller
@Mapping("/chat")
public class ChatController {

  @Inject
  private APIKeyService keyService;

  @Inject
  private ChatService chatService;

  @Post
  @Mapping("/complete")
  public SaResult complete(ChatRequest request) {
    StpUtil.checkLogin();
    var gptRequestInfo = new GPTRequestInfo(request.getMessage(), keyService.requestKey(request.getUser().getId()));
    try {
      return SaResult.data(chatService.complete(gptRequestInfo,request.getUser()));
    }catch (Exception e){
      e.printStackTrace();
      return SaResult.error(e.getMessage());
    }
  }

  @Delete
  @Mapping("/clear")
  public SaResult clear(User user) {
    StpUtil.checkLogin();
    chatService.clear(user.getId());
    return SaResult.ok();
  }
}

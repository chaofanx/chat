package xyz.chaofan.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
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
    GPTRequestInfo gptRequestInfo = new GPTRequestInfo(request.getMessage(), keyService.requestKey(request.getOpenid()));
    try {
      return SaResult.data(chatService.requestCompletions(gptRequestInfo,request.getOpenid()));
    }catch (Exception e){
      e.printStackTrace();
      return SaResult.error(e.getMessage());
    }
  }
}

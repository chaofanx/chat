package xyz.chaofan.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import xyz.chaofan.chatgpt.ApiAccessor;
import xyz.chaofan.entity.User;
import xyz.chaofan.entity.request.GPTRequestInfo;

@Service
public class ChatService {

  @Inject
  private UserService userService;

  @Inject
  private ApiAccessor apiAccessor;

  //流式
  public BufferedReader completeStream(GPTRequestInfo requestInfo, User user) {
    //请求api获取bufferedReader
    try (BufferedReader bufferedReader = apiAccessor.complete(requestInfo)) {
      //用户token-1
      userService.reduce(user.getOpenid());
      return bufferedReader;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //字符串
  public String requestCompletions(GPTRequestInfo requestInfo, String openid) throws Exception{
    try (BufferedReader bufferedReader = apiAccessor.complete(requestInfo)) {
      userService.reduce(openid);
      String message = IoUtil.read(bufferedReader);
      message = JSONUtil.parseObj(message).getByPath("$.choices[0].text", String.class);
      return message;
    }
  }

}

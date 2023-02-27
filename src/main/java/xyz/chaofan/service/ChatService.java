package xyz.chaofan.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import xyz.chaofan.chatgpt.ApiAccessor;
import xyz.chaofan.entity.Conversation;
import xyz.chaofan.entity.User;
import xyz.chaofan.entity.request.GPTRequestInfo;

@Service
public class ChatService {

  @Inject
  private UserService userService;

  @Inject
  private ApiAccessor apiAccessor;

  private Map<Long, Conversation> history = new HashMap<>();

  /**
   * 非阻塞式
   */
  @Tran
  public BufferedReader completeStream(GPTRequestInfo requestInfo, User user) {
    //请求api获取bufferedReader
    try (BufferedReader bufferedReader = apiAccessor.complete(requestInfo)) {
      //用户token-1
      userService.reduce(user.getId());
      return bufferedReader;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 阻塞式
   */
  @Tran
  public String complete(GPTRequestInfo requestInfo, User user) throws Exception{
    try (BufferedReader bufferedReader = apiAccessor.complete(requestInfo)) {
      userService.reduce(user.getId());
      var message = IoUtil.read(bufferedReader);
      message = JSONUtil.parseObj(message).getByPath("$.choices[0].text", String.class);
      var finalMessage = message;
      history.compute(user.getId(), (k, v) -> {
        if (v == null) {
          v = Conversation.newChat(user.getId());
        }
        v.append(requestInfo.getMessage(), finalMessage);
        return v;
      });
      return message;
    }
  }

  /**
   * 清理上下文
   */
  public void clear(Long id) {
    history.computeIfAbsent(id, k -> Conversation.newChat(id));
  }
}

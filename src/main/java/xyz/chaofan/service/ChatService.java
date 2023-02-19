package xyz.chaofan.service;

import cn.hutool.json.JSONUtil;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import xyz.chaofan.entity.User;
import xyz.chaofan.entity.request.GPTRequestInfo;
import xyz.chaofan.web.ApiAcessor;

import java.io.BufferedReader;

@Service
public class ChatService {

  @Inject
  private UserService userService;

  //流式
  public BufferedReader requestCompletionsStream(GPTRequestInfo requestInfo, User user) throws Exception{
    //请求api获取bufferedReader
    BufferedReader bufferedReader = ApiAcessor.requestCompletions(requestInfo);
    //用户token-1
    userService.reduce(user.getOpenid());
    return bufferedReader;
  }

  //字符串
  public String requestCompletions(GPTRequestInfo requestInfo, String openid) throws Exception{
    BufferedReader bufferedReader = ApiAcessor.requestCompletions(requestInfo);
    userService.reduce(openid);
    StringBuffer response = new StringBuffer();
    String message;
    while((message = bufferedReader.readLine()) != null){
      response.append(message);
    }
    String rsp = response.toString();
    rsp = JSONUtil.parseObj(rsp).getByPath("$.choices[0].text", String.class);
    return rsp;
  }

}

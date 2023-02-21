package xyz.chaofan.chatgpt;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import xyz.chaofan.entity.request.GPTRequestInfo;

@Component
public class ApiAccessor {

  @Inject("${app.openai.url}")
  private String url;

  /**
   * 通过stream字段设置是否使用流式传输
   */
  public BufferedReader complete(GPTRequestInfo requestInfo) {
    try (HttpResponse request = HttpUtil.createPost(url)
        .bearerAuth(requestInfo.getApiKey())
        .body(JSONUtil.createObj()
            .set("model", requestInfo.getModel())
            .set("prompt", requestInfo.getMessage())
            .set("max_tokens", requestInfo.getMaxToken())
            .set("stream", requestInfo.getStream())
            .toString()
        ).execute(requestInfo.getStream())) {
      if (!requestInfo.getStream()) {
        request.sync();
      }
      InputStream in = request.bodyStream();
      return new BufferedReader(new InputStreamReader(in));
    }
  }
}

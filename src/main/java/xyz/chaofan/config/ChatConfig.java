package xyz.chaofan.config;

import cn.hutool.core.io.FileUtil;
import com.theokanning.openai.service.OpenAiService;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;

@Configuration
public class ChatConfig {

  @Bean
  public OpenAiService openAiService() {
    String token = FileUtil.readUtf8String("token");
    return new OpenAiService(token);
  }
}

package xyz.chaofan.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;

@Service
public class ChatService {
  @Inject
  private OpenAiService openAiService;

  @Inject
  private UserService userService;

  @Inject("${app.model}")
  private String model;

  public String complete(String message, String openid) {
    CompletionRequest request = CompletionRequest.builder()
        .prompt(message)
        .model(model)
        .build();

    CompletionResult resp = openAiService.createCompletion(request);
    userService.reduce(openid);
    return resp.getChoices().get(0).getText();
  }

}

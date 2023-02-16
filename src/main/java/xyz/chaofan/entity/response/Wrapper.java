package xyz.chaofan.entity.response;

import lombok.Data;

@Data
public class Wrapper {
  private int code;
  private String message;
  private Object data;
  public static Wrapper success(Object data) {
    Wrapper wrapper = new Wrapper();
    wrapper.setCode(200);
    wrapper.setMessage("success");
    wrapper.setData(data);
    return wrapper;
  }
  public static Wrapper error(int code, String message) {
    Wrapper wrapper = new Wrapper();
    wrapper.setCode(code);
    wrapper.setMessage(message);
    return wrapper;
  }
}

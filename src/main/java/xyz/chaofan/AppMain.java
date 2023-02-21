package xyz.chaofan;

import org.noear.solon.Solon;

public class AppMain {

  public static void main(String[] args) {
    Solon.start(AppMain.class, args, app -> {
      app.enableWebSocket(true);
      app.enableWebSocketMvc(false);
    });
  }
}

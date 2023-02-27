package xyz.chaofan.controller;

import java.io.IOException;
import java.util.Map;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.ServerEndpoint;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.Session;
import xyz.chaofan.service.ChatService;

@ServerEndpoint(path = "/chat", method = MethodType.WEBSOCKET)
public class ChatSocket implements Listener {

  @Inject
  private ChatService chatService;

  private Map<String, Session> sessions;

  @Override
  public void onOpen(Session session) {
    var id = session.param("id");
    sessions.put(id, session);
    Listener.super.onOpen(session);
  }

  @Override
  public void onMessage(Session session, Message message) throws IOException {
    //message.setHandled(true); //设为true，则不进入mvc路由
    var msg = message.bodyAsString();
    session.send("我收到了：" + message.bodyAsString());

    //接收二进制
    //byte[] bytes = message.body();
    //发送二进制
    //session.send(Message.wrap(new byte[0]));
  }

  @Override
  public void onClose(Session session) {
    Listener.super.onClose(session);
  }

  @Override
  public void onError(Session session, Throwable error) {
    Listener.super.onError(session, error);
  }
}

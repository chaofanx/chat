package xyz.chaofan.entity;

import lombok.Getter;
import xyz.chaofan.util.ChatUtil;

/**
 * 对话历史记录
 */
public class Conversation {
    /**
     * 关联的用户id
     */
    @Getter
    private long id;

    private StringBuilder history = new StringBuilder();

    private Conversation(long id) {
        this.id = id;
    }

    /**
     * 构造一个对话对象
     */
    public static Conversation newChat(long id) {
        return new Conversation(id);
    }

    /**
     * 更新上下文
     */
    public Conversation append(String question, String answer) {
        history.append(ChatUtil.buildSentence(question, answer));
        return this;
    }

    /**
     * 清理缓存
     */
    public Conversation clear() {
        history = new StringBuilder();
        return this;
    }

    public String getHistory() {
        return history.toString();
    }

}

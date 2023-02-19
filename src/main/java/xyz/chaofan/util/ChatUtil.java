package xyz.chaofan.util;

public class ChatUtil {

    /**
     * 构造标准的对话记录
     */
    public static String buildSentence(String question, String answer) {
        return "Me:" + question + "\nYou:" + answer + "\n";
    }
}

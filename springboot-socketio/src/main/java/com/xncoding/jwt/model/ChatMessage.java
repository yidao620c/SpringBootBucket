package com.xncoding.jwt.model;

/**
 * ChatMessage
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/30
 */
public class ChatMessage {
    private String userName;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

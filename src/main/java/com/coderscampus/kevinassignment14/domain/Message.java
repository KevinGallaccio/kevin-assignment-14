package com.coderscampus.kevinassignment14.domain;

public class Message {
    private Long id;
    private User user;
    private String content;
    private Long channelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Message(User user, String content, Long channelId) {
        this.user = user;
        this.content = content;
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}

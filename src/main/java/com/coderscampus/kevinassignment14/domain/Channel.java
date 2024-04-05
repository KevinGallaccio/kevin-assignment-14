package com.coderscampus.kevinassignment14.domain;

import java.util.List;

public class Channel {
    private Long id;
    private List<Message> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Channel(Long id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", messages=" + messages +
                '}';
    }
}

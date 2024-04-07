package com.coderscampus.kevinassignment14.domain;


import java.util.List;


public class Channel {
    private Long id;
    private String name;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Channel(String name) {
        this.name = name;
    }


}

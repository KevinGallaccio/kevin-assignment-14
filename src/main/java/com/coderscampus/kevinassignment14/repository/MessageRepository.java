package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MessageRepository {
    private List<Message> messages;
    private AtomicLong idGenerator = new AtomicLong(1);

    public MessageRepository(List<Message> messages) {
        this.messages = messages;
    }

    public void save(Message message) {
        if (message.getId() == null) {
            message.setId(idGenerator.getAndIncrement());
        }
        messages.add(message);
    }

    public List<Message> findByChannelId(long channelId) {
        List<Message> messagesByChannelId = new ArrayList<>();
        for (Message message : messages) {
            if (message.getChannelId() == channelId) {
                messagesByChannelId.add(message);
            }
        }
        return messagesByChannelId;
    }
}

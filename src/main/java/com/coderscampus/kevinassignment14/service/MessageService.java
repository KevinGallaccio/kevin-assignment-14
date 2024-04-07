package com.coderscampus.kevinassignment14.service;

import com.coderscampus.kevinassignment14.domain.Message;
import com.coderscampus.kevinassignment14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save (Message message) {
        messageRepository.save(message);
        return message;
    }

    public List<Message> findMessagesByChannelId(Long channelId) {
        return messageRepository.findByChannelId(channelId);
    }
}

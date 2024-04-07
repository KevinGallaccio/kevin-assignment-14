package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import com.coderscampus.kevinassignment14.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {

    private MessageRepository messageRepository;
    private ChannelRepository channelRepository;

    @BeforeEach
    void setUp() {
        List<Message> messages = new ArrayList<>();
        messageRepository = new MessageRepository(messages); // Assign to class-level variable
        channelRepository = new ChannelRepository(messageRepository);
    }

    @Test
    @DisplayName("Should create, save and retrieve a message by channel Id")
    void testSaveAndFindByChannelId() {
        // Arrange
        User user = new User("username");
        Channel channel = new Channel("channelName");
        channelRepository.save(channel);

        //Act
        Message message = new Message(user, "Hello World!", channel.getId());
        messageRepository.save(message);
        List<Message> messages = messageRepository.findByChannelId(channel.getId());

        //Assert
        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }
}
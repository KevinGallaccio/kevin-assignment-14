package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import com.coderscampus.kevinassignment14.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryTests {

    private UserRepository userRepository;
    private ChannelRepository channelRepository;
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        List<Message> messages = new ArrayList<>();
        messageRepository = new MessageRepository(messages);
        userRepository = new UserRepository();
        channelRepository = new ChannelRepository(messageRepository);
    }

    @Test
    @DisplayName("Should create 2 Users posting different messages in two separate channels and retrieve them ")
    void testMultipleUsersMultipleMessagesInChannel() {
        // Arrange
        User user1 = new User("user1");
        User user2 = new User("user2");
        userRepository.save(user1);
        userRepository.save(user2);

        Channel channel1 = new Channel("General");
        channelRepository.save(channel1);
        String user1message1 ="Hello from User1";
        String user2message1 ="Hello from User2";

        Channel channel2 = new Channel("Second Channel");
        channelRepository.save(channel2);
        String user1message2 ="User1 is in Channel 2";
        String user1message3 ="User1 is waiting for User2";
        String user2message2 ="User2 is in Channel 2";

        // Act
        Message message1 = new Message(user1, user1message1, channel1.getId());
        Message message2 = new Message(user2, user2message1, channel1.getId());
        channelRepository.saveMessageToChannel(channel1, message1);
        channelRepository.saveMessageToChannel(channel1, message2);

        Message message3 = new Message(user1, user1message2, channel2.getId());
        Message message4 = new Message(user1, user1message3, channel2.getId());
        Message message5 = new Message(user2, user2message2, channel2.getId());
        channelRepository.saveMessageToChannel(channel2, message3);
        channelRepository.saveMessageToChannel(channel2, message4);
        channelRepository.saveMessageToChannel(channel2, message5);

        List<Message> messagesByChannelId1 = messageRepository.findByChannelId(channel1.getId());
        List<Message> messagesByChannelId2 = messageRepository.findByChannelId(channel2.getId());

        // Assert
        assertEquals(2, messagesByChannelId1.size());
        assertEquals(3, messagesByChannelId2.size());
        assertEquals("Hello from User1", channel1.getMessages().get(0).getContent());
        assertEquals("Hello from User2", channel1.getMessages().get(1).getContent());
        assertEquals("User1 is in Channel 2", channel2.getMessages().get(0).getContent());
        assertEquals("User1 is waiting for User2", channel2.getMessages().get(1).getContent());
        assertEquals("User2 is in Channel 2", channel2.getMessages().get(2).getContent());
    }
}


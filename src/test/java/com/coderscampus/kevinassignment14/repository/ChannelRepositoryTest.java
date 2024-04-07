package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.Channel;
import com.coderscampus.kevinassignment14.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChannelRepositoryTest {

    private ChannelRepository channelRepository;

    @BeforeEach
    void setUp() {
        List<Message> messages = new ArrayList<>();
        MessageRepository messageRepository = new MessageRepository(messages);
        channelRepository = new ChannelRepository(messageRepository);
    }

    @Test
    @DisplayName("Should create, save and retrieve a channel")
    void testSaveAndFindChannel() {
        // Arrange
        Channel channel = new Channel("General");

        // Act
        channelRepository.save(channel);
        Channel savedChannel = channelRepository.findById(channel.getId());

        // Assert
        assertNotNull(savedChannel);
        assertEquals(channel.getName(), savedChannel.getName());
    }

    @Test
    @DisplayName("Should create, save and retrieve multiple channels")
    void testFindAllChannels() {
        // Arrange
        Channel channel1 = new Channel("General");
        Channel channel2 = new Channel("Random");
        channelRepository.save(channel1);
        channelRepository.save(channel2);

        // Act
        List<Channel> channels = channelRepository.findAll();

        // Assert
        assertNotNull(channels);
        assertEquals(2, channels.size());
        assertTrue(channels.contains(channel1));
        assertTrue(channels.contains(channel2));
    }

    @Test
    @DisplayName("Should delete a channel")
    void testDeleteChannel() {
        // Arrange
        Channel channel = new Channel("General");
        channelRepository.save(channel);

        // Act
        channelRepository.delete(channel);
        Channel deletedChannel = channelRepository.findById(channel.getId());

        // Assert
        assertNull(deletedChannel);
    }
}
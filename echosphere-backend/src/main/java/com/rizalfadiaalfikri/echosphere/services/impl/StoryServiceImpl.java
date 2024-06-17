package com.rizalfadiaalfikri.echosphere.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Stories;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.repository.StoryRepository;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.StoryService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundDetailNullException;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Stories createStories(Stories stories, Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("Users with id " + userId + " is not found"));

        Stories newStories = new Stories();
        newStories.setCaptions(stories.getCaptions());
        newStories.setImage(stories.getImage());
        newStories.setUsers(users);

        return storyRepository.save(newStories);
    }

    @Override
    public List<Stories> findStoryByUserId(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundDetailNullException("Users with id " + userId + " is not found"));

        return storyRepository.findByUsers(users);
    }

}

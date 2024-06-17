package com.rizalfadiaalfikri.echosphere.services;

import java.util.List;

import com.rizalfadiaalfikri.echosphere.models.entity.Stories;

public interface StoryService {
    public Stories createStories(Stories stories, Long userId);

    public List<Stories> findStoryByUserId(Long userId);

}

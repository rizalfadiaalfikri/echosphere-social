package com.rizalfadiaalfikri.echosphere.services;

import java.util.List;

import com.rizalfadiaalfikri.echosphere.models.entity.Reels;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;

public interface ReelsService {
    public Reels createReels(Reels reels, Users users);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(Long userId);
}

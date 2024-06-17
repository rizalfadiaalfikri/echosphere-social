package com.rizalfadiaalfikri.echosphere.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizalfadiaalfikri.echosphere.models.entity.Reels;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;
import com.rizalfadiaalfikri.echosphere.repository.ReelsRepository;
import com.rizalfadiaalfikri.echosphere.repository.UserRepository;
import com.rizalfadiaalfikri.echosphere.services.ReelsService;
import com.rizalfadiaalfikri.echosphere.utils.exceptions.RowNotFoundException;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reels createReels(Reels reels, Users users) {
        Reels createReels = new Reels();
        createReels.setTitle(reels.getTitle());
        createReels.setVideo(reels.getVideo());
        createReels.setUsers(users);

        return reelsRepository.save(createReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(
                () -> new RowNotFoundException("Users with id " + userId + " is not found"));
        return reelsRepository.findByUsers(users);
    }

}

package com.rizalfadiaalfikri.echosphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rizalfadiaalfikri.echosphere.models.entity.Reels;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Long> {

    public List<Reels> findByUsers(Users users);

}
package com.rizalfadiaalfikri.echosphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rizalfadiaalfikri.echosphere.models.entity.Stories;
import com.rizalfadiaalfikri.echosphere.models.entity.Users;

@Repository
public interface StoryRepository extends JpaRepository<Stories, Long> {
    List<Stories> findByUsers(Users users);
}

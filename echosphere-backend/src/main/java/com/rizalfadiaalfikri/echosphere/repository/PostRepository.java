package com.rizalfadiaalfikri.echosphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rizalfadiaalfikri.echosphere.models.entity.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p where p.users.id =: userId")
    List<Posts> findPostByUserId(Long userId);
}

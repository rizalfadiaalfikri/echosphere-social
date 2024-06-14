package com.rizalfadiaalfikri.echosphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}

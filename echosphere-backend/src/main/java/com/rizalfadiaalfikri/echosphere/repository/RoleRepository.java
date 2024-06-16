package com.rizalfadiaalfikri.echosphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rizalfadiaalfikri.echosphere.models.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

}

package com.arth.ToDOApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.arth.ToDOApi.entities.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserDetails findByName(String username);
    Optional<UserEntity> findByEmail(String email);
}

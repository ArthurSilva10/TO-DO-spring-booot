package com.arth.ToDOApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arth.ToDOApi.entities.TaskEntity;
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>
{
    
}

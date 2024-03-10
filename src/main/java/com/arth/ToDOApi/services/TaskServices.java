package com.arth.ToDOApi.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arth.ToDOApi.DTOs.task.CreateTaskDTO;
import com.arth.ToDOApi.DTOs.task.TaskResponseDTO;
import com.arth.ToDOApi.entities.TaskEntity;
import com.arth.ToDOApi.entities.UserEntity;
import com.arth.ToDOApi.exeptions.DataNotFoundExeption;
import com.arth.ToDOApi.repository.UserRepository;

@Service
public class TaskServices 
{

    @Autowired
    private UserRepository userRepository;

    public TaskResponseDTO addTaskToUser(CreateTaskDTO dto)
    {
        Optional<UserEntity> user = userRepository.findByEmail(dto.Email());
        UserEntity userEntity = user.get();

        if(userEntity == null)
        {
            throw new DataNotFoundExeption("o usuário não está cadastrado no sistema");
        }
        else
        {
            TaskEntity entity = new TaskEntity();
            entity.setTitle(dto.title());
            entity.setDescription(dto.description());
            entity.setCreation_date(Instant.now());
            entity.setExpirationDate(dto.expiration());
            entity.setUser(user.get());
            userEntity.getTasks().add(entity);

            userEntity = userRepository.save(userEntity);

            return new TaskResponseDTO(entity.getTitle(), entity.getDescription(), entity.getExpirationDate(),
            entity.getCreation_date(), entity.getUser().getName());
        }
    }

    public List<TaskResponseDTO> getAllTaskOfUser(String email)
    {
        Optional<UserEntity> obj = userRepository.findByEmail(email);
        UserEntity user = obj.get();
        List<TaskResponseDTO> allTasks = new ArrayList<>();

        if(obj == null) throw new DataNotFoundExeption("o usuário não está cadastrado no sistema");

        else
        {
            List<TaskEntity> tasks = user.getTasks();
            tasks.stream().map(task -> allTasks.add(new TaskResponseDTO(task.getTitle(), task.getDescription(),
                            task.getExpirationDate(), task.getCreation_date(), task.getUser().getName())))
                            .toList();
            return allTasks;
        }
    }
}

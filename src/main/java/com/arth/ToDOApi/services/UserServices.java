package com.arth.ToDOApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.arth.ToDOApi.DTOs.task.TaskResponseDTO;
import com.arth.ToDOApi.DTOs.user.CreateUserDTO;
import com.arth.ToDOApi.DTOs.user.UserDTO;
import com.arth.ToDOApi.DTOs.user.UserLoginRequest;
import com.arth.ToDOApi.DTOs.user.UserLoginResposeDTO;
import com.arth.ToDOApi.DTOs.user.UserResponseDTO;
import com.arth.ToDOApi.entities.UserEntity;
import com.arth.ToDOApi.exeptions.DataNotFoundExeption;
import com.arth.ToDOApi.repository.UserRepository;
import com.arth.ToDOApi.security.SecurityConfig;
import com.arth.ToDOApi.security.TokenService;

@Service
public class UserServices 
{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public List<UserDTO> allUsers()
    {
        List<UserEntity> obj = userRepository.findAll();
        List<UserDTO> users = obj.stream()
            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole())).toList();
        
        return users;
    }

    public UserResponseDTO findById(String email)
    {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        List<TaskResponseDTO> tasksOfUser = taskServices.getAllTaskOfUser(email);
        return new UserResponseDTO(user.get().getId(), user.get().getName(),
        user.get().getEmail(), "***", user.get().getRole(), tasksOfUser);
        
    }

    public CreateUserDTO addUser(CreateUserDTO userDTO)
    {
        UserEntity user = new UserEntity();
        user.setName(userDTO.userName());
        user.setEmail(userDTO.email());
        user.setPassword(securityConfig.passwordEncoder().encode(userDTO.password()));
        user.setRole(userDTO.role());

        user = userRepository.save(user);
        return new CreateUserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getTasks());
    }

    public void deleteUser(long id)
    {
        if(userRepository.findById(id) == null)
        {
            throw new DataNotFoundExeption("o id de usuário selecionado não existe!");
        }

        userRepository.deleteById(id);
    }

    public UserLoginResposeDTO login(UserLoginRequest userLoginRequest)
    {
        var userNamePassword = new UsernamePasswordAuthenticationToken(userLoginRequest.name(), userLoginRequest.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new UserLoginResposeDTO(token);
    }
}
package com.example.singers_listing.service.impl;

import com.example.singers_listing.exception.WrongRequestException;
import com.example.singers_listing.exception.NotFoundException;
import com.example.singers_listing.model.dto.UserDTO;
import com.example.singers_listing.model.entity.User;
import com.example.singers_listing.repository.UserRepository;
import com.example.singers_listing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserDTO create(UserDTO dto){
        if (userRepository.existsByFirstNameAndLastName(dto.getFirstName(), dto.getLastName())) {
            throw new WrongRequestException("User with same firstName and lastName already exists");
        }
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .age(dto.getAge())
                .style(dto.getStyle())
                .build();

        user =  userRepository.save(user);
        return mapToDTO(user);
    }
    @Override
    public UserDTO getById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Singer not found"));
        return mapToDTO(user);
    }
    @Override
    public List<UserDTO> getAll(){
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public UserDTO update(Long id, UserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Singer not found"));

        if (userRepository.existsByFirstNameAndLastNameAndIdNot(dto.getFirstName(), dto.getLastName(), id)) {
            throw new WrongRequestException("Another singer with same firstName and lastName already exists");
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setGender(dto.getGender());
        user.setAge(dto.getAge());
        user.setStyle(dto.getStyle());

        user = userRepository.save(user);
        return mapToDTO(user);
    }
    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Singer not found");
        }
        userRepository.deleteById(id);
    }
    private UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .age(user.getAge())
                .style(user.getStyle())
                .build();
    }
}
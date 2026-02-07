package com.example.singers_listing.service;

import com.example.singers_listing.model.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    UserDTO getById(Long id);
    List<UserDTO> getAll();
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
}
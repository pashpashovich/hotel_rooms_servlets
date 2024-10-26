package ru.clevertec.hotelbooking.service;

import ru.clevertec.hotelbooking.dto.UserDTO;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.mapper.UserMapper;
import ru.clevertec.hotelbooking.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = UserMapper.INSTANCE;
    }

    public String registerUser(UserDTO userDto) {
        Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return "Логин уже существует";
        }
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return "Регистрация успешна";
    }
}

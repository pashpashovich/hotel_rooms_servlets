package ru.clevertec.hotelbooking.service;

import ru.clevertec.hotelbooking.dto.UserDTO;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.mapper.UserMapper;
import ru.clevertec.hotelbooking.repository.UserRepository;
import ru.clevertec.hotelbooking.util.Role;

import java.util.List;
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

    public List<UserDTO> getAllNonAdminUsers() {
        List<User> notAdmins = userRepository.findNotAdmins();
        return userMapper.toListDto(notAdmins);
    }

    public void deleteUser(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        byUsername.ifPresent(user -> userRepository.deleteById(user.getId()));
    }

    public void makeUserAdmin(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        byUsername.ifPresent(user -> userRepository.updateRole(user.getId(), Role.ADMIN.toString()));
    }
}

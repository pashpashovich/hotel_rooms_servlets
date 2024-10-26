package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.util.Role;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void save(User user);
    Optional<T> findByUsername(String username);
    void updateRole(long userId, String newRole);
    List<User> findNotAdmins();
    void deleteById(long userId);

}

package ru.clevertec.hotelbooking.entity;

import lombok.Data;
import ru.clevertec.hotelbooking.util.Role;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
}

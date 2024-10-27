package ru.clevertec.hotelbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.clevertec.hotelbooking.util.Role;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Role role;
}

package ru.clevertec.hotelbooking.dto;

import lombok.Data;
import ru.clevertec.hotelbooking.util.Role;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
}

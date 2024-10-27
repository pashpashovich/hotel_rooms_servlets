package ru.clevertec.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.hotelbooking.dto.UserDTO;
import ru.clevertec.hotelbooking.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDTO> toListDto(List<User> user);

    User toEntity(UserDTO userDTO);
}

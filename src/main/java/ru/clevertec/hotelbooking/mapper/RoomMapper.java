package ru.clevertec.hotelbooking.mapper;

import org.mapstruct.factory.Mappers;
import ru.clevertec.hotelbooking.dto.RoomDTO;
import ru.clevertec.hotelbooking.entity.Room;

public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDTO toDto(Room room);
    Room toEntity(RoomDTO roomDTO);
}

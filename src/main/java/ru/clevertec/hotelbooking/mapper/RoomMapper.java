package ru.clevertec.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.hotelbooking.dto.RoomDTO;
import ru.clevertec.hotelbooking.entity.Room;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    List<RoomDTO> toDto(List<Room> rooms);
}

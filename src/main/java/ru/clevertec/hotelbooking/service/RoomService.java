package ru.clevertec.hotelbooking.service;

import ru.clevertec.hotelbooking.dto.RoomDTO;
import ru.clevertec.hotelbooking.entity.Room;
import ru.clevertec.hotelbooking.mapper.RoomMapper;
import ru.clevertec.hotelbooking.repository.RoomRepository;

import java.math.BigDecimal;
import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.roomMapper=RoomMapper.INSTANCE;
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toDto(rooms);
    }

    public void addRoom(String roomType, BigDecimal price) {
        Room room = new Room(null, roomType, price);
        roomRepository.save(room);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.delete(roomId);
    }

    public List<RoomDTO> getAvailableRooms() {
        List<Room> availableRooms = roomRepository.findAvailableRooms();
        return roomMapper.toDto(availableRooms);
    }
}


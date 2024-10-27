package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Room;

import java.util.List;

public interface RoomRepository {
    void save(Room room);

    void delete(Long id);

    List<Room> findAll();

    List<Room> findAvailableRooms();
}

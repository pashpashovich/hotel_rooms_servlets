package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);
    void update(Room room);
    void delete(Long id);
    Optional<Room> findById(Long id);
    List<Room> findAll();
}

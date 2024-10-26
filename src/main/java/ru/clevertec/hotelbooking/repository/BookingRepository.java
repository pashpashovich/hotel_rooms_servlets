package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Booking;

import java.util.List;

public interface BookingRepository {
    void save(Booking booking);

    List<Booking> findByCustomerId(long customerId);
}


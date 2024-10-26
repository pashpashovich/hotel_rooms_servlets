package ru.clevertec.hotelbooking.entity;

import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDate;

@Data
public class Booking {
    private Long id;
    private Long customerId;
    private Long roomId;
    private LocalDate bookingDate;

    public Booking(Long customerId, Long roomId) {
        this.customerId = customerId;
        this.roomId = roomId;
    }
}

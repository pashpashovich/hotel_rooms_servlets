package ru.clevertec.hotelbooking.service;

import ru.clevertec.hotelbooking.dto.BookingDTO;
import ru.clevertec.hotelbooking.entity.Booking;
import ru.clevertec.hotelbooking.mapper.BookingMapper;
import ru.clevertec.hotelbooking.repository.BookingRepository;

import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper=BookingMapper.INSTANCE;
    }

    public List<BookingDTO> getBookingsByCustomerId(long customerId) {
        List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
        return bookingMapper.toDto(bookings);
    }

    public void createBooking(long customerId, long roomId) {
        Booking booking = new Booking(customerId, roomId);
        bookingRepository.save(booking);
    }
}


package ru.clevertec.hotelbooking.mapper;

import org.mapstruct.factory.Mappers;
import ru.clevertec.hotelbooking.dto.BookingDTO;
import ru.clevertec.hotelbooking.entity.Booking;

public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDTO toDto(Booking booking);
    Booking toEntity(BookingDTO bookingDTO);
}

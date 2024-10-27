package ru.clevertec.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.hotelbooking.dto.BookingDTO;
import ru.clevertec.hotelbooking.entity.Booking;

import java.util.List;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    List<BookingDTO> toDto(List<Booking> booking);
}

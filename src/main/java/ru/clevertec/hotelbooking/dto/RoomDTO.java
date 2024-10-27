package ru.clevertec.hotelbooking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDTO {
    private Long id;
    private String roomType;
    private BigDecimal price;
}

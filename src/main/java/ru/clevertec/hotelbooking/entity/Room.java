package ru.clevertec.hotelbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
        private Long id;
        private String roomType;
        private BigDecimal price;
}

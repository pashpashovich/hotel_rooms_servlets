package ru.clevertec.hotelbooking.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> findByUsername(String username);

}

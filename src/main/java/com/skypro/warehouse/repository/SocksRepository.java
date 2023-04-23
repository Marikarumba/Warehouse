package com.skypro.warehouse.repository;

import com.skypro.warehouse.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SocksRepository extends JpaRepository<Socks, Long> {
    boolean existsByColorAndCottonPart(String color, int cottonPart);
    Socks findByColorAndCottonPart(String color, int cottonPart);
}

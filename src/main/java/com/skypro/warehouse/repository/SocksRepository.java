package com.skypro.warehouse.repository;

import com.skypro.warehouse.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    boolean existsByColorAndCottonPart(String color, int cottonPart);
    Socks findByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findByColorAndCottonPartEquals(String color, int cottonPart);

    List<Socks> findByColorAndCottonPartLessThan(String color, int cottonPart);

    List<Socks> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
}

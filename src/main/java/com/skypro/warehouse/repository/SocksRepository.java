package com.skypro.warehouse.repository;

import com.skypro.warehouse.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    boolean existsByColorAndCottonPart(String color, int cottonPart);
    Socks findByColorAndCottonPart(String color, int cottonPart);
    List<Socks> findSocksByColorAndCottonPartEquals(String color, Integer cottonPart);

    List<Socks> findSocksByColorAndCottonPartLessThan(String color, Integer cottonPart);

    List<Socks> findSocksByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
}

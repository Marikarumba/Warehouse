package com.skypro.warehouse.service;

import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class SocksService {

    private final Socks socks;
    private final SocksRepository socksRepository;
    Logger logger = LoggerFactory.getLogger(SocksService.class);


    public Socks addSocks(String color, int cottonPart, int quantity) {
        logger.info("Was invoked method for addSocks");
        if (socksRepository.existsByColorAndCottonPart(color, cottonPart)) {
            Socks socks1 = socksRepository.findByColorAndCottonPart(color, cottonPart);
            socks1.setQuantity(socks1.getQuantity()+ quantity);
            logger.info("Found socks and add quantity");
            return socksRepository.save(socks1);
        } else {
            Socks socks1 = new Socks(1,color,cottonPart,quantity);
            logger.info("Socks added");
            return socksRepository.save(socks1);
        }
    }

    public Socks findSocks(long id) {
        logger.info("Was invoked method for findSocks");
       return null;
        //socksRepository.findById(id);
                //.orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден!")));;
    }

}

package com.skypro.warehouse.service;

import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksService {

    private final SocksRepository socksRepository;
    Logger logger = LoggerFactory.getLogger(SocksService.class);
    public SocksService( SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }
    public enum Operation {
        equal, moreThan, lessThan
    }

    public Socks incomeSocks(String color, int cottonPart, int quantity) {
        logger.info("Was invoked method for incomeSocks");
        if (socksRepository.existsByColorAndCottonPart(color, cottonPart)) {
            Socks socks1 = socksRepository.findByColorAndCottonPart(color, cottonPart);
            socks1.setQuantity(socks1.getQuantity()+ quantity);
            logger.info("Found socks and add quantity");
            return socksRepository.save(socks1);
        } else {
            Socks socks1 = new Socks();
            socks1.setColor(color);
            socks1.setCottonPart(cottonPart);
            socks1.setQuantity(quantity);
            logger.info("Socks added");
            return socksRepository.save(socks1);
        }
    }

    public List<Socks> findSocks(String color, Operation operation, int cottonPart) {
        logger.info("Was invoked method for getSocks");
        List <Socks> findSocks = new ArrayList<>();
        switch (operation) {
            case equal -> findSocks = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);
            case lessThan -> findSocks = socksRepository.findByColorAndCottonPartLessThan(color, cottonPart);
            case moreThan -> findSocks = socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart);
        }
        return findSocks;
    }

    public Socks outcomeSocks(String color, int cottonPart, int quantity) {
        return null;
    }


}

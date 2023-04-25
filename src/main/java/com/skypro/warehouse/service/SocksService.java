package com.skypro.warehouse.service;

import com.skypro.warehouse.dto.SocksDto;
import com.skypro.warehouse.dto.SocksListDto;
import com.skypro.warehouse.exception.LackOfSocksException;
import com.skypro.warehouse.exception.SocksNotFoundException;
import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocksService {

    public SocksService( SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }
    private final SocksRepository socksRepository;
    Logger logger = LoggerFactory.getLogger(SocksService.class);


    public Socks incomeSocks(SocksDto inSocks) {
        logger.info("Was invoked method for incomeSocks");
        if (socksRepository.existsByColorAndCottonPart(inSocks.getColor(), inSocks.getCottonPart())) {
            Socks socks1 = socksRepository.findByColorAndCottonPart(inSocks.getColor(), inSocks.getCottonPart());
            socks1.setQuantity(socks1.getQuantity()+ inSocks.getQuantity());
            logger.info("Found socks and add quantity");
            return socksRepository.save(socks1);
        } else {
            Socks socks1 = new Socks();
            socks1.setColor(inSocks.getColor());
            socks1.setCottonPart(inSocks.getCottonPart());
            socks1.setQuantity(inSocks.getQuantity());
            logger.info("Socks added");
            return socksRepository.save(socks1);
        }
    }


    public Socks outcomeSocks(SocksDto inSocks) {
        logger.info("Was invoked method for outcomeSocks");
        if (socksRepository.existsByColorAndCottonPart(inSocks.getColor(), inSocks.getCottonPart())) {
            Socks socks1 = socksRepository.findByColorAndCottonPart(inSocks.getColor(), inSocks.getCottonPart());
            socks1.setQuantity(socks1.getQuantity() - inSocks.getQuantity());
            if (socks1.getQuantity() >= 0){
                logger.info("Found socks and reduce quantity");
                return socksRepository.save(socks1);
            } else {
                logger.info("Not enough socks");
                throw new LackOfSocksException ("Not enough socks. Balance: " + socks1.getQuantity());
            }
        } else {
            logger.info("Socks not Found");
            throw new SocksNotFoundException("Socks not Found");
        }
    }

    public List<Socks> getSocks(SocksListDto ask ) {
        logger.info("Was invoked method for getSocks");
        List<Socks> findSocks = new ArrayList<>();
        switch (ask.getOperation()) {
            case equal -> findSocks = socksRepository.findSocksByColorAndCottonPartEquals(ask.getColor(), ask.getCottonPart());
            case lessThan -> findSocks = socksRepository.findSocksByColorAndCottonPartLessThan(ask.getColor(), ask.getCottonPart());
            case moreThan -> findSocks = socksRepository.findSocksByColorAndCottonPartGreaterThan(ask.getColor(), ask.getCottonPart());
        }
        return findSocks;
    }

}

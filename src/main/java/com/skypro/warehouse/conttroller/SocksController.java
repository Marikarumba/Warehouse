package com.skypro.warehouse.conttroller;

import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.service.SocksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocksController {

    private final SocksService socksService;
    Logger logger = LoggerFactory.getLogger(SocksService.class);
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping ( "/api/socks/income")
    public ResponseEntity<Socks> addSocks(@RequestParam String color, @RequestParam int cottonPart, @RequestParam int quantity) {
        logger.info("Request for add socks");
        return ResponseEntity.ok(socksService.addSocks(color, cottonPart,quantity));
    }
}

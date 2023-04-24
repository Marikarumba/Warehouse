package com.skypro.warehouse.conttroller;

import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.service.SocksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;
    Logger logger = LoggerFactory.getLogger(SocksService.class);
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping ("/income")
    public ResponseEntity<Socks> addSocks(@RequestParam String color, @RequestParam int cottonPart, @RequestParam int quantity) {
        logger.info("Request for add socks");
        if ( color.isEmpty() || cottonPart  <0 || cottonPart > 101 || quantity<0 ){

        }
        return ResponseEntity.ok(socksService.incomeSocks(color, cottonPart,quantity));
    }

    @PostMapping("/outcome")
    public ResponseEntity<Socks> outcomeSocks(@RequestParam String color,
                                                    @RequestParam int cottonPart,
                                                    @RequestParam int quantity) {
        return ResponseEntity.ok(socksService.outcomeSocks(color, cottonPart, quantity));
    }
}

package com.skypro.warehouse.conttroller;

import com.skypro.warehouse.dto.SocksDto;
import com.skypro.warehouse.dto.SocksListDto;
import com.skypro.warehouse.dto.SocksOperations;
import com.skypro.warehouse.model.Socks;
import com.skypro.warehouse.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    @Operation(summary = "Приход носков на склад",
            responses = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class)) }),
            @ApiResponse(responseCode = "400", description = "Parameters Error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    public ResponseEntity<Socks> addSocks(@Valid @RequestBody SocksDto Socks) {
        logger.info("Request for add socks");
        return ResponseEntity.ok(socksService.incomeSocks(Socks));
    }

    @PostMapping("/outcome")
    @Operation(summary = "Выдача носков со склада",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class)) }),
                    @ApiResponse(responseCode = "400", description = "Parameters Error", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    public ResponseEntity<Socks> outcomeSocks(@Valid @RequestBody SocksDto Socks) {
        logger.info("Request for out socks");
        return ResponseEntity.ok(socksService.outcomeSocks(Socks));
    }
    @GetMapping()
    @Operation(summary = "Запрос наличия носков на склада",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class)) }),
                    @ApiResponse(responseCode = "400", description = "Parameters Error", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    public ResponseEntity<List<Socks>> getSocks(@RequestParam String color,
                                                @RequestParam String operation,
                                                @RequestParam int cottonPart) {
        if (color.isBlank() || color.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (!(operation.equals("MORE_THAN")||operation.equals("LESS_THAN")||operation.equals("EQUAL") )) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if ((cottonPart<1) || (cottonPart>100) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        SocksListDto ask=new SocksListDto();
        ask.setColor(color);
        ask.setCottonPart(cottonPart);
        ask.setOperation( SocksOperations.valueOf(operation) );

        return ResponseEntity.ok(socksService.getSocks(ask));
    }

}

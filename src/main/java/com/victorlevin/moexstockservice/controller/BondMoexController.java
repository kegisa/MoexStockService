package com.victorlevin.moexstockservice.controller;

import com.victorlevin.moexstockservice.dto.FigiesDto;
import com.victorlevin.moexstockservice.dto.StocksDto;
import com.victorlevin.moexstockservice.dto.StocksPricesDto;
import com.victorlevin.moexstockservice.dto.TickersDto;
import com.victorlevin.moexstockservice.service.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bonds")
@RequiredArgsConstructor
public class BondMoexController {
    private final BondService bondService;

    @PostMapping("/getBondsByTickers")
    public StocksDto getBondsFromMoex(@RequestBody TickersDto tickersDto) {
        return bondService.getBondsFromMoex(tickersDto);
    }

    @PostMapping("/prices")
    public StocksPricesDto getPricesByFigies(@RequestBody FigiesDto figiesDto) {
        return bondService.getPricesByFigies(figiesDto);
    }
}

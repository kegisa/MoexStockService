package com.victorlevin.moexstockservice.service;

import com.victorlevin.moexstockservice.dto.*;
import com.victorlevin.moexstockservice.exception.BondNotFoundException;
import com.victorlevin.moexstockservice.exception.LimitRequestsException;
import com.victorlevin.moexstockservice.model.Currency;
import com.victorlevin.moexstockservice.model.Stock;
import com.victorlevin.moexstockservice.moexclient.CorporateBondsClient;
import com.victorlevin.moexstockservice.moexclient.GovBondsClient;
import com.victorlevin.moexstockservice.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BondService {
    private final BondRepository bondRepository;


    public StocksDto getBondsFromMoex(TickersDto tickersDto) {
        log.info("Request for tickers {}", tickersDto.getTickers());
        List<String> tickers = new ArrayList<>(tickersDto.getTickers());


        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        List<BondDto> resultBonds = allBonds.stream()
                .filter(b -> tickers.contains(b.getTicker()))
                .collect(Collectors.toList());

        List<Stock> stocks =  resultBonds.stream().map(b -> {
            return Stock.builder()
                    .ticker(b.getTicker())
                    .name(b.getName())
                    .figi(b.getTicker())
                    .type("Bond")
                    .currency(Currency.RUB)
                    .source("MOEX")
                    .build();
        }).collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    public StocksPricesDto getPricesByFigies(FigiesDto figiesDto) {
        log.info("Request for figies {}", figiesDto.getFigies());
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        figies.removeAll(allBonds.stream().map(b -> b.getTicker()).collect(Collectors.toList()));
        if(!figies.isEmpty()) {
            throw new BondNotFoundException(String.format("Bonds %s not found.", figies));
        }
        List<StockPrice> prices = allBonds.stream()
                .filter(b -> figiesDto.getFigies().contains(b.getTicker()))
                .map(b -> new StockPrice(b.getTicker(), b.getPrice() * 10))
                .collect(Collectors.toList());
        return new StocksPricesDto(prices);
    }


}

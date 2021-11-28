package com.victorlevin.moexstockservice.dto;

import com.victorlevin.moexstockservice.model.Stock;
import lombok.*;

import java.util.List;

@Value
public class StocksDto {
    List<Stock> stocks;
}

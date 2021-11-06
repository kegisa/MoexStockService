package com.victorlevin.moexstockservice.parser;


import com.victorlevin.moexstockservice.dto.BondDto;

import java.util.List;

public interface Parser {
    List<BondDto> parse(String ratesAsString);
}

package com.victorlevin.moexstockservice.moexclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "govbonds", url = "${moex.bonds.government.url}", configuration = FeignConfig.class)
public interface GovBondsClient {
    @GetMapping
    String getBondsFromMoex();
}

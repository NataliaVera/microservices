package com.msvc.usuario.external.services;

import com.msvc.usuario.entity.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATE-SERVICE")
public interface RateService {

    @PostMapping("/rates/createrate")
    public ResponseEntity<Rate> createRate(Rate rate);

    @PutMapping("/rates/update/{rateid}")
    public ResponseEntity<Rate> updateRate(@PathVariable String rateid, Rate rate);

    @DeleteMapping("/rates/delete/{rateid}")
    void deleterate(@PathVariable String rateid);


}

package com.msvc.rate.controller;

import com.msvc.rate.entity.Rate;
import com.msvc.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping("/createrate")
    public ResponseEntity<Rate> saveRate(@RequestBody Rate rate){
        return ResponseEntity.status(HttpStatus.CREATED).body(rateService.create(rate));
    }

    @GetMapping("/allrates")
    public ResponseEntity<List<Rate>> getAllRates(){
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @GetMapping("/byusers/{userid}")
    public ResponseEntity<List<Rate>> getAllRatesByUser(@PathVariable String userid){
        return ResponseEntity.status(HttpStatus.OK).body(rateService.getAllRatesByUserId(userid));
    }

    @GetMapping("/byhotels/{hotelid}")
    public ResponseEntity<List<Rate>> getAllRatesByHotel(@PathVariable String hotelid){
        return ResponseEntity.status(HttpStatus.OK).body(rateService.getAllRatesByHotelId(hotelid));
    }

    @PutMapping("/rates/update/{rateid}")
    public ResponseEntity<Rate> updateRate(@PathVariable String rateid,@RequestBody Rate rate){
        return null;
    }

    @DeleteMapping("/rates/delete/{rateid}")
    public ResponseEntity<Rate> deleteRate(@PathVariable String rateid){
        return null;
    }
}

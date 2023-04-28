package com.msvc.rate.service;

import com.msvc.rate.entity.Rate;

import java.util.List;

public interface RateService {

    Rate create(Rate rate);
    List<Rate> getAllRates();
    List<Rate> getAllRatesByUserId(String userId);
    List<Rate> getAllRatesByHotelId(String hotelId);

}

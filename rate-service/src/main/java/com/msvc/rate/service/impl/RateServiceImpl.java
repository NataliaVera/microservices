package com.msvc.rate.service.impl;

import com.msvc.rate.entity.Rate;
import com.msvc.rate.repository.RateRepository;
import com.msvc.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate create(Rate rate) {
        String rateId = UUID.randomUUID().toString();
        rate.setRateId(rateId);
        return rateRepository.save(rate);
    }

    @Override
    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public List<Rate> getAllRatesByUserId(String userId) {
        return rateRepository.findByUserId(userId);
    }

    @Override
    public List<Rate> getAllRatesByHotelId(String hotelId) {
        return rateRepository.findByHotelId(hotelId);
    }
}

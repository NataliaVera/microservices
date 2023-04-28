package com.msvc.rate.repository;

import com.msvc.rate.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, String> {

    List<Rate> findByUserId(String userId);
    List<Rate> findByHotelId(String hotelId);
}

package com.msvc.hote.service;

import com.msvc.hote.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotelById(String id);
}

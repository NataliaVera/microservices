package com.msvc.usuario.service.impl;

import com.msvc.usuario.entity.Hotel;
import com.msvc.usuario.entity.Rate;
import com.msvc.usuario.entity.User;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.repository.UserRepository;
import com.msvc.usuario.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("Usuario NO encontrado"));

        Rate[] userRatings =
                restTemplate.getForObject("http://RATE-SERVICE/rates/byusers/"+user.getUserId(), Rate[].class);
        List<Rate> rates = Arrays.stream(userRatings).collect(Collectors.toList());
        logger.info("{}", userRatings);

        List<Rate> rateList = rates.stream().map(rate -> {
            System.out.println(rate.getHotelId());
            ResponseEntity<Hotel> forEntity =
                    restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/getbyid/"+rate.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Respuesta con código de estado: {}", forEntity.getStatusCode());

            hotel.setHotelId(rate.getHotelId());
            rate.setHotel(hotel);
            return rate;
        }).collect(Collectors.toList());

        user.setRateList(rateList);
        return user;
    }
}

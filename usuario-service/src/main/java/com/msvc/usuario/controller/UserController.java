package com.msvc.usuario.controller;

import com.msvc.usuario.entity.User;
import com.msvc.usuario.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User userRequest){
        User user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    int cantidadReintentos = 1;
    @GetMapping("/{userid}")
    //@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userid){
        log.info("Listar un solo usuario: Usuario controller");
        log.info("Cantidad reintentos: {}", cantidadReintentos);
        cantidadReintentos++;
        User user = userService.getUserById(userid);

        return ResponseEntity.ok(user);
    }
    
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception){
        log.info("El respaldo se ejecuta porque el servicio esta inactivo: ",exception.getMessage());
        User user = User.builder()
                .email("usuario5@email.com")
                .name("Usuario5")
                .information("Este usuario se crea por defecto cuando un servicio se cae")
                .userId("1234")
                .build();
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

}

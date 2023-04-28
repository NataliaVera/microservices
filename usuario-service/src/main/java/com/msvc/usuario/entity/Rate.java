package com.msvc.usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    private String rateId;
    private String userId;
    private String hotelId;
    private int rate;
    private String comment;
    private Hotel hotel;
}

package com.msvc.rate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Document("rate")
@Entity
public class Rate {

    @Id
    private String rateId;
    private String userId;
    private String hotelId;
    private int rate;
    private String comment;
}

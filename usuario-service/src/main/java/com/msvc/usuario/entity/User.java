package com.msvc.usuario.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String information;
    @Transient //No se va a persistir en la DB
    private List<Rate> rateList = new ArrayList<>();
}

package com.bitboxer2.SpringBootAPI.model;

import com.bitboxer2.SpringBootAPI.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Desactivated {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long desactivatedId;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

}

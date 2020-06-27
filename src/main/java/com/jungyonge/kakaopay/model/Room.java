package com.jungyonge.kakaopay.model;

import lombok.Data;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    @NaturalId
    @Column(name = "room_num")
    private long num;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Room(){
        super();
    }

}

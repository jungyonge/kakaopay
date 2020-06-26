package com.jungyonge.kakaopay.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id")
    private long id;

    private int money;

//    @ManyToMany
//    @JoinTable(name = "user_room",
//                joinColumns = @JoinColumn(name="user_id"),
//                inverseJoinColumns = @JoinColumn(name = "room_id"))
//    private List<Room> rooms = new ArrayList<Room>();

    @OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER)
    private List<Room> rooms = new ArrayList<>();


    @CreationTimestamp
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date udtDate;

    public User(){
        super();
    }

}

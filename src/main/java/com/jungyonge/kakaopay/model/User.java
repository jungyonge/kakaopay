package com.jungyonge.kakaopay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"rooms"})
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

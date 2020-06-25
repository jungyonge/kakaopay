package com.jungyonge.kakaopay.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private long id;

    @ManyToMany(mappedBy = "rooms")
    private List<MemberInfo> members = new ArrayList<MemberInfo>();
//

}

package com.jungyonge.kakaopay.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ShareEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "share_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "room_num",
            referencedColumnName = "room_num" )
    private Room room;

    private String token;

    private int totalShareMoney;

    private int totalSharePeople;

    private boolean expired;

    @OneToMany(mappedBy = "shareEvent", fetch = FetchType.EAGER)
    private List<ShareEventDetail> shareEventDetails;

    @CreationTimestamp
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date udtDate;

    @Builder
    public ShareEvent(long id ,User user, Room room, String token, int totalShareMoney , int totalSharePeople, boolean expired, Date regDate, Date udtDate){
        this.id = id;
        this.user = user;
        this.room = room;
        this.token = token;
        this.totalShareMoney = totalShareMoney;
        this.totalSharePeople = totalSharePeople;
        this.expired = expired;
    }

    public ShareEvent(){
        super();
    }

}

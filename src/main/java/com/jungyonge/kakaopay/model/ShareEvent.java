package com.jungyonge.kakaopay.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ShareEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "share_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberInfo memberInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    private String token;

    private long totalShareMoney;

    private long totalSharePeople;

    private boolean expired;

    @CreationTimestamp
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date udtDate;
}

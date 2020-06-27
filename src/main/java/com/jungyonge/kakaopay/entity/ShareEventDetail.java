package com.jungyonge.kakaopay.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class ShareEventDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "share_detail_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "share_id")
    private ShareEvent shareEvent;

    private int shareMoneyDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date udtDate;

    public ShareEventDetail(){
        super();
    }
}

package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.entity.ShareEventDetail;
import com.jungyonge.kakaopay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareEventDetailRepository  extends JpaRepository<ShareEventDetail, Long> {
    List<ShareEventDetail> findByShareEventIdAndUserIsNull(long shareEvent_id);

    long countByShareEventIdAndUserIs(long shareEvent_id, User user);



}

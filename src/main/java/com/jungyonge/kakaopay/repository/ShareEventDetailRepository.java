package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.model.ShareEventDetail;
import com.jungyonge.kakaopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareEventDetailRepository  extends JpaRepository<ShareEventDetail, Long> {
    List<ShareEventDetail> findByShareEventIdAndUserIsNull(long shareEvent_id);

    List<ShareEventDetail> findByShareEventId(long id);

    long countByShareEventIdAndUserIs(long shareEvent_id, User user);



}

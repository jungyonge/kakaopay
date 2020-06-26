package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.model.Room;
import com.jungyonge.kakaopay.model.ShareEvent;
import com.jungyonge.kakaopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareEventRepository extends JpaRepository<ShareEvent, Long> {

    ShareEvent findById(long id);
    ShareEvent findByIdAndUserIsNotNull(long id);

    ShareEvent findByRoomAndToken(Room room, String token);

    ShareEvent findByRoomAndTokenAndUser(Room room, String token, User user);



}

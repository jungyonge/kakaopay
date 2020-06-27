package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.entity.Room;
import com.jungyonge.kakaopay.entity.ShareEvent;
import com.jungyonge.kakaopay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareEventRepository extends JpaRepository<ShareEvent, Long> {

    ShareEvent findById(long id);

    ShareEvent findByRoomAndToken(Room room, String token);

    ShareEvent findByRoomAndTokenAndUser(Room room, String token, User user);



}

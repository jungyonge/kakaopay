package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.model.Room;
import com.jungyonge.kakaopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findById(long id);

    Room findTopByNum(long num);
    Room findByNumAndUser(long num, User user);

    Room findByIdAndUser(long id , User user);

    List<Room> findByNum(long num);


}

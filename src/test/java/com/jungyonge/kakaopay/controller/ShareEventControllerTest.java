package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.model.Room;
import com.jungyonge.kakaopay.model.User;
import com.jungyonge.kakaopay.repository.RoomRepository;
import com.jungyonge.kakaopay.repository.UserRepository;
import com.jungyonge.kakaopay.service.ShareEventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class ShareEventControllerTest {

    @Autowired
    private ShareEventService shareEventService;

    @Autowired
    private ShareEventController shareEventController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeClass
    public void initDummyData(){
        User user = new User();
        user.setMoney(100);
        userRepository.save(user);
        user.setMoney(200);
        userRepository.save(user);
        user.setMoney(300);
        userRepository.save(user);

        Room room = new Room();
        room.setNum(1);
        room.setUser(userRepository.findById(1));
        roomRepository.save(room);
        room.setNum(1);
        room.setUser(userRepository.findById(2));
        roomRepository.save(room);
        room.setNum(1);
        room.setUser(userRepository.findById(3));
        roomRepository.save(room);

    }

//    @Test
//    void addShareEvent() {
//        int xUserId = 3;
//        int xRoomId = 3;
//        int totalShareMoney = 100;
//        int totalSharePeople = 3;
//
//        ResponseEntity responseEntity = shareEventController.addShareEvent(xUserId,xRoomId,totalShareMoney,totalSharePeople);
//
//        log.info(responseEntity.toString());
//    }
//
//    @Test
//    void attendShareEvent() throws Exception {
//        int xUserId = 1;
//        int xRoomId = 3;
//        String token = "6an";
//
//        ResponseEntity responseEntity = shareEventController.attendShareEvent(xUserId,xRoomId,token);
//        log.info(responseEntity.toString());
//    }
//
//    @Test
//    void searchShareEvent() {
//        int xUserId = 3;
//        int xRoomId = 3;
//        String token = "6an";
//        ResponseEntity responseEntity = shareEventController.searchShareEvent(xUserId,xRoomId,token);
//        log.info(responseEntity.toString());
//    }
}
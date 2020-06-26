package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.model.Room;
import com.jungyonge.kakaopay.model.ShareEvent;
import com.jungyonge.kakaopay.model.User;
import com.jungyonge.kakaopay.service.ShareEventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ShareEventControllerTest {

    @Autowired
    private ShareEventService shareEventService;

    @Autowired
    private ShareEventController shareEventController;

    @Test
    void addShareEvent() {
        int xUserId = 3;
        int xRoomId = 5;
        int totalShareMoney = 100;
        int totalSharePeople = 3;

        ResponseEntity responseEntity = shareEventController.addShareEvent(xUserId,xRoomId,totalShareMoney,totalSharePeople);

        log.info(responseEntity.toString());
    }

    @Test
    void attendShareEvent() throws Exception {
        int xUserId = 1;
        int xRoomId = 3;
        String token = "6an";

        ResponseEntity responseEntity = shareEventController.attendShareEvent(xUserId,xRoomId,token);
        log.info(responseEntity.toString());
    }
}
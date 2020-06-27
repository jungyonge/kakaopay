package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.entity.Room;
import com.jungyonge.kakaopay.entity.User;
import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.model.request.AddShareEventRequest;
import com.jungyonge.kakaopay.repository.RoomRepository;
import com.jungyonge.kakaopay.repository.UserRepository;
import com.jungyonge.kakaopay.service.ShareEventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShareEventControllerTest {

    @Mock
    private ShareEventService shareEventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

//    @BeforeClass
//    public void initDummyData(){
//        User user = new User();
//        user.setMoney(100);
//        userRepository.save(user);
//        user.setMoney(200);
//        userRepository.save(user);
//        user.setMoney(300);
//        userRepository.save(user);
//
//        Room room = new Room();
//        room.setNum(1);
//        room.setUser(userRepository.findById(1));
//        roomRepository.save(room);
//        room.setNum(1);
//        room.setUser(userRepository.findById(2));
//        roomRepository.save(room);
//        room.setNum(1);
//        room.setUser(userRepository.findById(3));
//        roomRepository.save(room);
//
//    }

    @Test
    public void addShareEvent() {
        int xUserId = 3;
        int xRoomId = 3;


        AddShareEventRequest addShareEventRequest = mock(AddShareEventRequest.class);
        shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);

        when(shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest)).thenReturn("111");
        when(shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest)).thenThrow(new ShareEventException(ShareEventException.ResponseCode.E0006));


    }

    @Test
    public void attendShareEvent() throws Exception {
        int xUserId = 1;
        int xRoomId = 3;
        String token = "6an";

    }

    @Test
    public void searchShareEvent() {
        int xUserId = 3;
        int xRoomId = 3;
        String token = "6an";

    }

}
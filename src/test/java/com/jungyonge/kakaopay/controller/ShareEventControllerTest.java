package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.entity.Room;
import com.jungyonge.kakaopay.entity.User;
import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.model.request.AddShareEventRequest;
import com.jungyonge.kakaopay.model.request.AttendShareEventRequest;
import com.jungyonge.kakaopay.repository.RoomRepository;
import com.jungyonge.kakaopay.repository.UserRepository;
import com.jungyonge.kakaopay.service.ShareEventService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShareEventControllerTest {

    @Autowired
    private ShareEventService shareEventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;



    @Before
    public void initData(){
        User user = new User();
        Room room = new Room();
        user.setId(1);
        user.setMoney(100);
        userRepository.save(user);
        room.setId(1);
        room.setNum(1);
        room.setUser(user);
        roomRepository.save(room);

        user = new User();
        room = new Room();
        user.setId(2);
        user.setMoney(200);
        userRepository.save(user);
        room.setId(2);
        room.setNum(1);
        room.setUser(user);
        roomRepository.save(room);

        user = new User();
        room = new Room();
        user.setId(3);
        user.setMoney(300);
        userRepository.save(user);
        room.setId(3);
        room.setNum(3);
        room.setUser(user);
        roomRepository.save(room);
    }

    @Test
    @DisplayName("token 값 길이 확인")
    public void makeTokenTest() {
        int xUserId = 3;
        int xRoomId = 3;
        String expectToken = "aQ1";

        AddShareEventRequest addShareEventRequest = mock(AddShareEventRequest.class);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);
        assertEquals(expectToken.length(),testToken.length());
    }

    @Test(expected = ShareEventException.class)
    @DisplayName("방 참여 여부 확인")
    public void joinRoomTest() {
        int xUserId = 1;
        int xRoomId = 3;
        String expectToken = "aQ1";

        AddShareEventRequest addShareEventRequest = mock(AddShareEventRequest.class);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);
        assertEquals(expectToken.length(),testToken.length());
    }

    @Test(expected = ShareEventException.class)
    @DisplayName("만든 이벤트 참여 불가능 확인")
    public void eventHostTest() {
        int xUserId = 1;
        int xRoomId = 1;

        AddShareEventRequest addShareEventRequest = mock(AddShareEventRequest.class);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);

        AttendShareEventRequest attendShareEventRequest = new AttendShareEventRequest();
        attendShareEventRequest.setToken(testToken);
        int shareMoney = shareEventService.attendShareEvent(xUserId,xRoomId,attendShareEventRequest);
    }

    @Test(expected = ShareEventException.class)
    @DisplayName("같은 대화방에 속해 있는지 확인")
    public void sameRoomTest() {
        int xUserId = 1;
        int xRoomId = 1;

        AddShareEventRequest addShareEventRequest = mock(AddShareEventRequest.class);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);

        xUserId = 3;
        xRoomId = 3;
        AttendShareEventRequest attendShareEventRequest = new AttendShareEventRequest();
        attendShareEventRequest.setToken(testToken);
        int shareMoney = shareEventService.attendShareEvent(xUserId,xRoomId,attendShareEventRequest);
    }

    @Test(expected = ShareEventException.class)
    @DisplayName("이벤트 재참여 불가능 확인")
    public void duplicateAttendTest() {
        int xUserId = 1;
        int xRoomId = 1;

        AddShareEventRequest addShareEventRequest = new AddShareEventRequest();
        addShareEventRequest.setTotalShareMoney(100);
        addShareEventRequest.setTotalSharePeople(3);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);

        xUserId = 2;
        xRoomId = 2;
        AttendShareEventRequest attendShareEventRequest = new AttendShareEventRequest();
        attendShareEventRequest.setToken(testToken);
        int shareMoney = shareEventService.attendShareEvent(xUserId,xRoomId,attendShareEventRequest);

        shareMoney = shareEventService.attendShareEvent(xUserId,xRoomId,attendShareEventRequest);
    }

    @Test
    @DisplayName("정상적으로 이벤트 생성 참여")
    public void addShareEventTest() {
        int xUserId = 1;
        int xRoomId = 1;

        AddShareEventRequest addShareEventRequest = new AddShareEventRequest();
        addShareEventRequest.setTotalShareMoney(100);
        addShareEventRequest.setTotalSharePeople(3);
        String testToken = shareEventService.addShareEvent(xUserId,xRoomId,addShareEventRequest);

        xUserId = 2;
        xRoomId = 2;

        AttendShareEventRequest attendShareEventRequest = new AttendShareEventRequest();
        attendShareEventRequest.setToken(testToken);
        int shareMoney = shareEventService.attendShareEvent(xUserId,xRoomId,attendShareEventRequest);
    }



}
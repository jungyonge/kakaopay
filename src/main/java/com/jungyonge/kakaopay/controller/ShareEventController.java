package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.model.ShareEvent;
import com.jungyonge.kakaopay.service.ShareEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("share-event")
public class ShareEventController extends ResponseAbstractController {

    private final ShareEventService shareEventService;

    public ShareEventController(ShareEventService shareEventService) {
        this.shareEventService = shareEventService;
    }


    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity addShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                        @RequestHeader("X-ROOM-ID") int xRoomId,
                                        @RequestParam int totalShareMoney,
                                        @RequestParam int totalSharePeople) {
        try {
            return makeResponse(shareEventService.addShareEvent(xUserId, xRoomId, totalShareMoney,totalSharePeople ));
        } catch (ShareEventException e) {
            return makeResponse(HttpStatus.BAD_REQUEST,e.getErrorMsg(),e.getErrorCode());
        }

    }

    @PutMapping("/attend")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity attendShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                           @RequestHeader("X-ROOM-ID") int xRoomId,
                                           @RequestParam String token) {
        try {
            return makeResponse(shareEventService.attendShareEvent(xUserId, xRoomId, token));
        } catch (ShareEventException e) {
            return makeResponse(HttpStatus.BAD_REQUEST,e.getErrorMsg(),e.getErrorCode());
        }
    }

}

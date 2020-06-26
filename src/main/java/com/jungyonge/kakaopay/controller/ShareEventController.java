package com.jungyonge.kakaopay.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.payload.jsonHint.JsonHint;
import com.jungyonge.kakaopay.service.ShareEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/share-event")
public class ShareEventController extends ResponseAbstractController {

    private final ShareEventService shareEventService;

    public ShareEventController(ShareEventService shareEventService) {
        this.shareEventService = shareEventService;
    }

    @GetMapping("/test")
    public ResponseEntity hello() {
        return makeResponse();
    }

    @GetMapping("/data")
    public ResponseEntity data() {
        return makeResponse(new HashMap<String, String>() {{
            this.put("gg","gg");
            this.put("gg2","gg2");
            this.put("gg3","gg3");
        }});
    }


    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity addShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                        @RequestHeader("X-ROOM-ID") int xRoomId,
                                        @RequestParam(value = "totalShareMoney") int totalShareMoney,
                                        @RequestParam(value = "totalSharePeople") int totalSharePeople)
    {
        try {
            return makeResponse(shareEventService.addShareEvent(xUserId, xRoomId, totalShareMoney,totalSharePeople ));
        } catch (ShareEventException e) {
            return makeResponse(HttpStatus.BAD_REQUEST,e.getResponseMsg(),e.getResponseCode());
        }

    }

    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity attendShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                           @RequestHeader("X-ROOM-ID") int xRoomId,
                                           @RequestParam(value = "token") String token)
    {
        try {
            return makeResponse(shareEventService.attendShareEvent(xUserId, xRoomId, token));
        } catch (ShareEventException e) {
            return makeResponse(HttpStatus.BAD_REQUEST,e.getResponseMsg(),e.getResponseMsg());
        }
    }

    @GetMapping("/search")
    public ResponseEntity searchShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                           @RequestHeader("X-ROOM-ID") int xRoomId,
                                           @RequestParam(value = "token") String token)
    {
        try {
            return makeResponse(shareEventService.searchShareEvent(xUserId, xRoomId, token));
        } catch (ShareEventException e) {
            return makeResponse(HttpStatus.BAD_REQUEST,e.getResponseMsg(),e.getResponseMsg());
        }
    }

}

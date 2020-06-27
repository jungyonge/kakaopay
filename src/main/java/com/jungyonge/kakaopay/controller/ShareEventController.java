package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.exception.ShareEventException;
import com.jungyonge.kakaopay.model.request.AddShareEventRequest;
import com.jungyonge.kakaopay.model.request.AttendShareEventRequest;
import com.jungyonge.kakaopay.model.request.SearchShareEventRequest;
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

    @PostMapping
    public ResponseEntity addShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                        @RequestHeader("X-ROOM-ID") int xRoomId,
                                        @RequestBody AddShareEventRequest request) {
        return makeResponse(shareEventService.addShareEvent(xUserId, xRoomId, request));
    }

    @PutMapping
    public ResponseEntity attendShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                           @RequestHeader("X-ROOM-ID") int xRoomId,
                                           @RequestBody AttendShareEventRequest request) {
        return makeResponse(shareEventService.attendShareEvent(xUserId, xRoomId, request));

    }

    @GetMapping
    public ResponseEntity searchShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                           @RequestHeader("X-ROOM-ID") int xRoomId,
                                           @RequestBody SearchShareEventRequest request) {
        return makeResponse(shareEventService.searchShareEvent(xUserId, xRoomId, request));

    }

}

package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.model.request.AddShareEventRequest;
import com.jungyonge.kakaopay.model.request.AttendShareEventRequest;
import com.jungyonge.kakaopay.model.request.SearchShareEventRequest;
import com.jungyonge.kakaopay.model.response.AddShareEventResponse;
import com.jungyonge.kakaopay.model.response.AttendShareEventResponse;
import com.jungyonge.kakaopay.model.response.SearchShareEventResponse;
import com.jungyonge.kakaopay.service.ShareEventService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/share-event")
public class ShareEventController {

    private final ShareEventService shareEventService;

    public ShareEventController(ShareEventService shareEventService) {
        this.shareEventService = shareEventService;
    }

    @PostMapping
    public AddShareEventResponse addShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                               @RequestHeader("X-ROOM-ID") int xRoomId,
                                               @RequestBody AddShareEventRequest request) {
        return AddShareEventResponse.of(shareEventService.addShareEvent(xUserId, xRoomId, request));
    }

    @PutMapping
    public AttendShareEventResponse attendShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                                     @RequestHeader("X-ROOM-ID") int xRoomId,
                                                     @RequestBody AttendShareEventRequest request) {
        return AttendShareEventResponse.of(shareEventService.attendShareEvent(xUserId, xRoomId, request));

    }

    @GetMapping
    public SearchShareEventResponse searchShareEvent(@RequestHeader("X-USER-ID") int xUserId,
                                                     @RequestHeader("X-ROOM-ID") int xRoomId,
                                                     @RequestBody SearchShareEventRequest request) {
        return SearchShareEventResponse.of(shareEventService.searchShareEvent(xUserId, xRoomId, request));

    }

}

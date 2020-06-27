package com.jungyonge.kakaopay.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendShareEventResponse {
    int shareMoney;


    public static AttendShareEventResponse of(int shareMoney) {
        return AttendShareEventResponse.builder()
                .shareMoney(shareMoney)
                .build();
    }
}

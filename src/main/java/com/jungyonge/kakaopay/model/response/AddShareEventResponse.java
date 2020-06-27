package com.jungyonge.kakaopay.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddShareEventResponse {
    String token;

    public static AddShareEventResponse of(String token) {
        return AddShareEventResponse.builder()
                .token(token)
                .build();
    }
}

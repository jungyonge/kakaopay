package com.jungyonge.kakaopay.model.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String code;
    private String message;

    @Builder
    public ErrorResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

}
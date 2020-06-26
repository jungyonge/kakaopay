package com.jungyonge.kakaopay.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse {

    private int status;

    private String responseMsg;

    private String responseCode;

    private Object data;

    public ApiResponse() {
        this(HttpStatus.OK);
    }

    public ApiResponse(HttpStatus status) {
        this.status = status.value();
        this.responseMsg = status.getReasonPhrase();
        this.responseCode = "C0000";
    }

    public ApiResponse(HttpStatus status, String responseCode) {
        this.status = status.value();
        this.responseMsg = status.getReasonPhrase();
        this.responseCode = responseCode;
    }

    public ApiResponse(HttpStatus status, String responseCode, String responseMsg) {
        this.status = status.value();
        this.responseMsg = responseMsg;
        this.responseCode = responseCode;
    }

}

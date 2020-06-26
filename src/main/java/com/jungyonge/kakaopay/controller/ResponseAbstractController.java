package com.jungyonge.kakaopay.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jungyonge.kakaopay.payload.ApiResponse;
import com.jungyonge.kakaopay.payload.jsonHint.JsonHint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseAbstractController {

    protected ResponseEntity makeResponse() {
        return ResponseEntity.ok(new ApiResponse() {{

        }});
    }

    protected ResponseEntity makeResponse(Object data) {
        return ResponseEntity.ok(new ApiResponse() {{
            this.setData(data);
        }});
    }

    protected ResponseEntity makeResponse(HttpStatus httpStatus,String responseMsg, String responseCode) {
        return ResponseEntity.ok(new ApiResponse() {{
            this.setStatus(httpStatus.value());
            this.setResponseCode(responseMsg);
            this.setResponseCode(responseCode);
        }});
    }


}

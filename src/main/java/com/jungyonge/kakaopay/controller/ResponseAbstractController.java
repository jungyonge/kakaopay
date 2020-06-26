package com.jungyonge.kakaopay.controller;

import com.jungyonge.kakaopay.payload.ApiResponse;
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

    protected ResponseEntity makeResponse(HttpStatus httpStatus,String errorMsg, String errorCode) {
        return ResponseEntity.ok(new ApiResponse() {{
            this.setStatus(httpStatus.value());
            this.setErrorMsg(errorMsg);
            this.setErrorCode(errorCode);
        }});
    }


}

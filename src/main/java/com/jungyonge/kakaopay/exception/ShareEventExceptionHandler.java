package com.jungyonge.kakaopay.exception;

import com.jungyonge.kakaopay.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@ControllerAdvice
@RestController
public class ShareEventExceptionHandler {

    @ExceptionHandler(value = ShareEventException.class)
    public ResponseEntity<ErrorResponse> shareEventException(ShareEventException e){
        log.error(e.getResponseMsg());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getResponseCode(),e.getResponseMsg()));
    }

}

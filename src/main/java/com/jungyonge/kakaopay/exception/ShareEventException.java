package com.jungyonge.kakaopay.exception;

import lombok.Getter;

public class ShareEventException extends RuntimeException{

    @Getter
    private String responseCode;

    @Getter
    private String responseMsg;

    public ShareEventException(String message) {
        super(message);
    }

    public ShareEventException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ShareEventException(Throwable throwable) {
        super(throwable);
    }

    public ShareEventException(ResponseCode responseCode) {
        //super(MessageSourceUtil.getMessage(errorCode.toString()));
        super(responseCode.getValue());
        this.responseMsg = responseCode.getValue();
        this.responseCode = responseCode.toString();
    }

    public enum ResponseCode {

        C0000("정상"),
        E0001("이미 참여한 계정입니다."),
        E0002("해당 뿌리기를 만드신 계정은 참여 불가능 합니다."),
        E0003("만료된 뿌리기 입니다."),
        E0004("뿌리기가 존재하지 않습니다"),
        E0005("계정 정보가 존재하지 않습니다."),
        E0006("채팅방에 속한 유저만 이벤트에 참여 가능합니다. "),
        E0007("참여하신 채팅방이 아닙니다."),
        E0312("결제금액 계산 시 오류가 발생했습니다"),
        E0313("보너스 스타가 부족합니다")       ;
        private String value;

        ResponseCode(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public String toString() {
            return super.name().replace("C", "");
        }
    }

}

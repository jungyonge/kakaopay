package com.jungyonge.kakaopay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareEventDto {
    private Date regDate;
    private int totalShareMoney;
    private int completeShareMoney;
    private List<ShareEventDetailDto> shareEventDetails;

    //    public ShareEventDetail toEntity(){
//        return new ShareEventDetail(regDate,totalShareMoney,completeShareMoney,shareEventDetails);
//    }


}

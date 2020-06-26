package com.jungyonge.kakaopay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShareEventDetailDto {

    private int shareMoneyDetail;
    private long userId;

}

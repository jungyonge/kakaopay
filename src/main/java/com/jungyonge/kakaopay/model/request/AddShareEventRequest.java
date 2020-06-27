package com.jungyonge.kakaopay.model.request;

import lombok.Data;

@Data
public class AddShareEventRequest {
    int totalShareMoney;
    int totalSharePeople;
}

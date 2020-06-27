package com.jungyonge.kakaopay.model.response;

import com.jungyonge.kakaopay.model.ShareEventDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchShareEventResponse {

    ShareEventDto shareEventDto;

    public static SearchShareEventResponse of(ShareEventDto shareEventDto) {
        return SearchShareEventResponse.builder()
                .shareEventDto(shareEventDto)
                .build();
    }
}

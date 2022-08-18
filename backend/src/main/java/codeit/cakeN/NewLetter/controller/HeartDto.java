package codeit.cakeN.NewLetter.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class HeartDto {
    private String campaignId;
    private String userId;
}

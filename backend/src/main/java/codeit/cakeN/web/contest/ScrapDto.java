package codeit.cakeN.web.contest;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScrapDto {
    private Long userId;
    private Long contestId;
}

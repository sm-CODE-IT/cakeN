package codeit.cakeN.web.dto;

import codeit.cakeN.domain.entity.Contest;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContestUpdateDto {

    private Integer post_id;

    @NotBlank(message = "이미지를 첨부해주세요.")
    private String post_image;

    private String post_name;

    private String created_at;

    private String post_des;

    private Integer user_id;

    @Builder
    public ContestUpdateDto(String post_image, String post_name, String post_des) {
        this.post_image = post_image;
        this.post_name = post_name;
        this.post_des = post_des;
    }

    public Contest toEntity() {
        return Contest.builder()
                .post_image(post_image)
                .post_name(post_name)
                .post_des(post_des)
                .build();
    }

}

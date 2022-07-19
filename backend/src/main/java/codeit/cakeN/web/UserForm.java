package codeit.cakeN.web;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserForm {

    private String email;

    private String pw;

    private String intro;

    private String image;

    private String nickname;

}

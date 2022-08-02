package codeit.cakeN.web.dto;


import java.util.Optional;


public record UserUpdateDto(Optional<String> nickname, Optional<String> intro, Optional<String> image) {


}

package codeit.cakeN.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime time;

    private int status;
    private String errorMessage;

    public CustomErrorResponse(String errorMessage, int status) {
        this.time = LocalDateTime.now();
        this.errorMessage = errorMessage;
        this.status = status;
    }
}

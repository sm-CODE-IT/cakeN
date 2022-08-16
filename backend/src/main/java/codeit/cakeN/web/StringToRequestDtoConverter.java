/*
package codeit.cakeN.web;

import codeit.cakeN.web.user.dto.UserRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class StringToRequestDtoConverter extends Throwable implements Converter<String, UserRequestDto> {

    private ObjectMapper objectMapper;
    UserRequestDto userRequestDto;

    public StringToRequestDtoConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    */
/**
     * 변환된 DTO에서는 BindingResult 사용 불가 => 직접 비즈니스 로직 내에서 검증 로직을 작성해야 함
     * @param source
     * @return
     *//*

    @Override
    @SneakyThrows
    public UserRequestDto convert(String source) {
        userRequestDto = objectMapper.readValue(source, new TypeReference<UserRequestDto>(){});

        return userRequestDto;
    }
}
*/

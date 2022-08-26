package codeit.cakeN.web.design;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.domain.design.DesignRepository;
import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.design.DesignService;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import codeit.cakeN.web.design.dto.DesignUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DesignApiController {

    private final DesignService designService;
    private final DesignRepository designRepository;
    private final HttpSession httpSession;
    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;


/**
     * 케이크 디자인 생성 처리
     * @param designRequestDto
     * @param formUser
     * @return
     */

    @PostMapping("/design")
    public ResponseEntity<Design> saveDesign(@Valid @RequestBody DesignRequestDto designRequestDto, @AuthenticationPrincipal User formUser) {

        /*if (bindingResult.hasErrors()) {
            red;
    }*/
        /*// 에러 검증 로직 (BindingResult 대체)
        Map<String, String> errorsMap = checkParameterValid(designRequestDto);
        if (errorsMap.size() >= 1) {
            ResponseJsonObject exceptionDto = new ParamValidationException(errorsMap).getResponseJsonObject();
            return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        }*/

        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);

        designRequestDto.setUser(user);
        designService.save(designRequestDto);

        return ResponseEntity.ok(designRequestDto.toEntity());
    }

    private Map<String, String> checkParameterValid(DesignRequestDto requestDto) {
        Map<String, String> errorsMap = new HashMap<>();
        String defaultMessage;

        defaultMessage = "필수 입력 값입니다.";
        errorsMap.put("errors", defaultMessage);

        return errorsMap;
    }

    /*private Design convertToEntity(DesignRequestDto designRequestDto) {
        Design design = modelMapper.map(designRequestDto, Design.class);

        if (design.getDesignId() != null) {
            System.out.println("Old Design Called");
        }
        return design;
    }*/


/**
     * 케이크 디자인 리스트 모아보기
     * @param model
     * @return
     */

    @GetMapping("/design/list")
    public List<Design> designList(Model model, @AuthenticationPrincipal User formUser) {
        return this.designRepository.findAll();
    }

/**
     * 케이크 디자인 정보 디테일 페이지
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/design/{id}")
    public ResponseEntity<Design> designDetail(@PathVariable Long id, Model model) {

        // 작성자 닉네임 가져오기
        codeit.cakeN.domain.user.User user = userRepository.findById(designService.showInfo(id).getUser().getUserId()).get();
        model.addAttribute("userName", user.getNickname());

        DesignRequestDto designRequestDto = designService.showInfo(id);
        return ResponseEntity.ok(designRequestDto.toEntity());
    }

/**
     * 케이크 디자인 정보 수정
     * @param id
     * @param updateDto
     * @return
     */
    @PutMapping("/design/{id}")
    public ResponseEntity updateDesign(@PathVariable Long id, @RequestBody DesignUpdateDto updateDto) {
        designService.update(id, updateDto);
        return ResponseEntity.ok().build();
    }


    /**
     * 케이크 디자인 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/design/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        designService.delete(id);
        return ResponseEntity.ok().build();
    }
}

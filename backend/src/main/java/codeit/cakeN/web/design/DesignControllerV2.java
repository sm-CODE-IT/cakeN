/*
package codeit.cakeN.web.design;

import codeit.cakeN.domain.user.UserRepository;
import codeit.cakeN.service.design.DesignService;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import codeit.cakeN.web.design.dto.DesignUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static codeit.cakeN.web.user.UserController.findSessionUser;

@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignControllerV2 {

    private final DesignService designService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;


    */
/**
     * 케이크 디자인 생성 페이지
     * @param designRequestDto
     * @return
     *//*

    @GetMapping("/")
    public String saveDesign(@ModelAttribute("designRequestDto") DesignRequestDto designRequestDto) {
        return "design/createDesignForm";
    }




    */
/**
     * 케이크 디자인 리스트 모아보기
     * @param model
     * @return
     *//*

    //TODO 현재 접속한 사용자의 케이크 디자인만 리스트업
    */
/*@GetMapping("/list")
    public String designList(Model model, @AuthenticationPrincipal User formUser) {
        // 작성자 닉네임 가져오기
        codeit.cakeN.domain.user.User user = findSessionUser(formUser, httpSession, userRepository);

        model.addAttribute("designs", user.getDesignList());

        return "design/designList";
    }*//*


    */
/**
     * 케이크 디자인 정보 디테일 페이지
     * @param id
     * @param model
     * @return
     *//*

    */
/*@GetMapping("/detail/{id}")
    public String designDetail(@PathVariable Long id, Model model) {
        model.addAttribute("design", designService.showInfo(id));

        // 작성자 닉네임 가져오기
        codeit.cakeN.domain.user.User user = userRepository.findById(designService.showInfo(id).getUser().getUserId()).get();
        model.addAttribute("userName", user.getNickname());

        return "design/designDetail";
    }*//*


    */
/**
     * 케이크 디자인 정보 수정 페이지
     * @param id
     * @param model
     * @return
     *//*

    @GetMapping("/update/{id}")
    public String updateDesign(@PathVariable Long id, Model model) {
        DesignRequestDto designRequestDto = designService.showInfo(id);
        model.addAttribute("designRequestDto", designRequestDto);
        return "design/updateDesignForm";
    }



    */
/**
     * 케이크 디자인 삭제
     * @param id
     * @return
     *//*

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        designService.delete(id);
        return "redirect:/design/list";
    }

}
*/

package codeit.cakeN.web.design;

import codeit.cakeN.domain.design.Design;
import codeit.cakeN.service.design.DesignService;
import codeit.cakeN.web.design.dto.DesignRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignController {

    private final DesignService designService;

    /**
     * 케이크 디자인 생성 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String saveDesign(Model model) {
        model.addAttribute("designRequestDto", new DesignRequestDto());
        return "design/createDesignForm";
    }

    /**
     * 케이크 디자인 생성 처리
     * @param designRequestDto
     * @return
     */
    @PostMapping("/")
    public String saveDesign(DesignRequestDto designRequestDto) {
        designService.save(designRequestDto);

        return "redirect:/design/list";
    }

    /**
     * 케이크 디자인 리스트 모아보기
     * @param model
     * @return
     */
    //TODO 현재 접속한 사용자의 케이크 디자인만 리스트업
    @GetMapping("/list")
    public String designList(Model model) {
        model.addAttribute("designs", designService.showAllDesign());
        return "design/designList";
    }

    /**
     * 케이크 디자인 정보 디테일 페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String designDetail(@PathVariable Long id, Model model) {
        model.addAttribute("design", designService.showInfo(id));
        return "design/designDetail";
    }

    /**
     * 케이크 디자인 정보 수정 페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateDesign(@PathVariable Long id, Model model) {
        model.addAttribute("designRequestDto", new DesignRequestDto());
        return "design/updateDesignForm";
    }

    /**
     * 케이크 디자인 정보 수정 로직
     * @param designRequestDto
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateDesign(DesignRequestDto designRequestDto) {
        designService.update(designRequestDto);
        return "redirect:/design/list";
    }

    /**
     * 케이크 디자인 삭제
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        designService.delete(id);
        return "redirect:/design/list";
    }

}

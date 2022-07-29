package codeit.cakeN.web.controller;

import codeit.cakeN.domain.entity.Contest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// @RequestMapping("/contest")
public class ContestController {

    @GetMapping("/contest/create")
    public String contestCreate() {
        return "contest/create";
    }

    @GetMapping("/contest/update")
    public String contestUpdate(Contest contest) {
        System.out.println("content : " + contest.getPost_image());
        System.out.println("content : " + contest.getPost_des());     // 왜
        return "test";
    }


}

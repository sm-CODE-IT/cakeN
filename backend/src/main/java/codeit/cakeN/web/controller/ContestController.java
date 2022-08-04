package codeit.cakeN.web.controller;

import codeit.cakeN.domain.entity.contest.Contest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contest")
public class ContestController {

    @GetMapping("/create")
    public String contestCreate() {
        return "contest/create";
    }

    @GetMapping("/update")
    public String contestUpdate(Contest contest) {
        System.out.println("content : " + contest.getPost_image());
        System.out.println("content : " + contest.getPost_des());     // 왜
        return "contest/test";
    }


}

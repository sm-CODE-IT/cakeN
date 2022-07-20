package codeit.cakeN.web.controller;

import codeit.cakeN.web.entity.Contest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/contest")
public class ContestController {


    @GetMapping("/contest/create")
    public String contestCreate() {
        return "contest/create";
    }

    @PostMapping("/contest/update")
    public String contestUpdate(Contest contest) {
        System.out.println("content : " + contest.getPost_image());
        System.out.println("content : " + contest.getPost_des());     // 왜
        return "test";
    }


}

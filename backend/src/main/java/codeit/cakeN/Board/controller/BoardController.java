package codeit.cakeN.Board.controller;

import codeit.cakeN.Board.domain.Board;
import codeit.cakeN.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String BlankPage() {
        return "board/blank";
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("board", boardService.list());
        return "board/list";
    }

    @GetMapping("/detail/{idx}")
    public String detail(@PathVariable int idx, Model model) {

        model.addAttribute("board", boardService.detail(idx));
        return "board/detail";
    }

    @GetMapping("/register")
    public String registerGet() {

        return "board/register";
    }

    @PostMapping("/register")
    public String registerPost(Board board) {

        boardService.register(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{idx}")
    public String updateGet(@PathVariable int idx, Model model) {

        model.addAttribute("board", boardService.detail(idx));
        return "board/update";
    }

    @PostMapping("/update")
    public String updatePost(Board board) {

        boardService.update(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable int idx) {

        boardService.delete(idx);
        return "redirect:/board/list";
    }
}
package com.example.board.controller;

import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    //mybatis 연동 test
    @GetMapping("/now")
    public String now(Model model) throws Exception {
        model.addAttribute("now", boardService.now());
        return "now";
    }
}

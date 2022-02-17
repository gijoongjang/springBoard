package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

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

    @GetMapping
    public String root() throws Exception {
        return "index";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm() throws Exception {
        return "boardWrite";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(@Validated BoardVO boardVO, Errors errors, Model model) throws Exception {
        if(errors.hasErrors()) {
            model.addAttribute("boardVO", boardVO);
            Map<String, String> validationResult = boardService.validateHandling(errors);
            for (String key : validationResult.keySet()) {
                model.addAttribute(key, validationResult.get(key));
            }
            return "boardWrite";
        }

        boardService.boardWrite(boardVO);

        return "boardWrite";
        //return "boardList"
    }

    //TODO 게시판 리스트 작성
}

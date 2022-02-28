package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import com.example.board.vo.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:boardList";
    }

//    @GetMapping("/boardList")
//    public String boardList(Model model
//                , @RequestParam(required = false, defaultValue = "1") int page
//                , @RequestParam(required = false, defaultValue = "1") int range) throws Exception {
//        int boardCount = boardService.getBoardCount();
//
//        Paging paging = new Paging();
//        paging.pageInfo(page, range, boardCount);
//
//        model.addAttribute("boardList", boardService.getBoardList(paging));
//        model.addAttribute("paging", paging);
//
//        return "boardList";
//    }

    @GetMapping("/boardList")
    public String boardList(Criteria criteria, Model model) throws Exception {
        int boardCount = boardService.getBoardCount(criteria);

        model.addAttribute("boardList", boardService.getList(criteria));
        model.addAttribute("pageInfo", new PageDTO(criteria, boardCount));

        return "boardList";
    }

    @GetMapping("/boardDetail")
    public String boardRead(@RequestParam("no") int no, Model model) throws Exception {
        BoardVO boardVO = boardService.getDetailBoard(no);

        model.addAttribute("boardVO", boardVO);

        return "boardDetail";
    }

    //TODO 게시판 수정, 삭제
}

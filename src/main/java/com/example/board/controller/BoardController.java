package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import com.example.board.vo.PageDTO;
import com.example.board.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
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

    @GetMapping()
    public String root() throws Exception {
        return "index";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm() throws Exception {
        return "boardWrite";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(@Validated BoardVO boardVO, Errors errors, Model model) throws Exception {
        if (errors.hasErrors()) {
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
    public String boardRead(@RequestParam("no") int no,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        BoardVO boardVO = boardService.getDetailBoard(no);

        model.addAttribute("boardVO", boardVO);

        //조회수 쿠키 체크
        Cookie[] cookies = request.getCookies();
        Cookie viewCookie = null;

        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("cookie"+no)) {
                    viewCookie = cookie;
                }
            }
        }

        if(viewCookie == null) {
            Cookie newCookie = new Cookie("cookie"+no, "|" + no + "|");

            response.addCookie(newCookie);

            try {
                boardService.viewNoUp(no);
                System.out.println("조회수증가");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "boardDetail";
    }

    @GetMapping("/boardModifyForm")
    public String boardModifyForm(@RequestParam("no") int no, Model model) throws Exception {
        BoardVO boardVO = boardService.getDetailBoard(no);

        model.addAttribute("modifyData", boardVO);

        return "boardModify";
    }

    @PostMapping("/boardModify")
    public String boardModify(BoardVO boardVO) throws Exception {
        try {
            boardService.boardModify(boardVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardModify";
    }

    @GetMapping("/boardDelete")
    public ModelAndView boardDelete(@RequestParam("no") int no) throws Exception {
        ModelAndView mav = new ModelAndView();

        try {
            mav.addObject("message", "success");
            boardService.boardDelete(no);
        } catch (Exception e) {
            mav.addObject("message", "fail");
            e.printStackTrace();
        }

        mav.setViewName("forward:boardDetail?no=" + no);

        return mav;
    }

    @GetMapping("/login")
    public String login() throws Exception {
        return "login";
    }

//    @RequestMapping(value="/index", method = {RequestMethod.GET, RequestMethod.POST})
//    public String userLogin(UserVO userVO, HttpServletRequest request, RedirectAttributes ra) throws Exception {
//        HttpSession session = request.getSession();
//        UserVO user = boardService.userLogin(userVO);
//
//        if(user == null) {
//            session.setAttribute("user", null);
//        } else {
//            session.setAttribute("user", user);
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "signUp";
    }

    @PostMapping("/signUp")
    @ResponseBody
    public Map<String, Object> signUp(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (!bindingResult.hasErrors()) {
            result.put("message", true);
            boardService.createUser(userVO);
        } else {
            result.put("message", false);
            result.put("errMessage", boardService.getErrorMessage(bindingResult));
        }

        return result;
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestBody String id) throws Exception {
        int userCount = boardService.idCheck(id);

        return userCount;
    }

    @GetMapping("/loginFail")
    public String loginFail(RedirectAttributes rttr
            , @RequestParam(value = "error", required = false) String error
            , @RequestParam(value = "exception", required = false) String exception) {

        rttr.addFlashAttribute("error", error);
        rttr.addFlashAttribute("exception", exception);

        return "redirect:/login";
    }
}

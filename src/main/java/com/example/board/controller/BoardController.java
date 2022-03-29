package com.example.board.controller;

import com.example.board.util.FileUtil;
import com.example.board.security.CustomUserDetails;
import com.example.board.service.BoardService;
import com.example.board.vo.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    @Value("${file.path}")
    private String filePath;

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
    @ResponseBody
    public Map<String, Object> boardWrite(@RequestPart( value = "data", required = false) @Validated BoardVO boardVO, BindingResult bindingResult
            , @RequestPart(value = "uploadFiles", required = false) List<MultipartFile> fileList
            , @AuthenticationPrincipal CustomUserDetails user) throws Exception {

        FileVO fileVO = new FileVO();
        int maxBoardNo = boardService.getBoardNo();
        Map<String, Object> retMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            retMap.put("succeed", false);
            retMap.put("errMessage", boardService.getErrorMessage(bindingResult));
            return retMap;
        }

        boardService.boardWrite(boardVO);

        File uploadPath = new File(filePath + FileUtil.makePath());

        if(fileList != null) {
            fileList.forEach(x->{
                String fileName = x.getOriginalFilename();

                if(!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                File saveFile = new File(uploadPath, fileName);

                fileVO.setBno(maxBoardNo);
                fileVO.setOriginal_filename(fileName);
                fileVO.setFilename(FileUtil.getNewFileName(fileName));
                fileVO.setUpload_path(uploadPath + "\\" + fileName);
                fileVO.setFilesize(x.getSize());
                fileVO.setFiletype(FileUtil.getContentType(fileName));
                fileVO.setCreateuser(user.getName());   //로그인 유저

                try {
                    x.transferTo(saveFile);
                    boardService.insertFiles(fileVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        retMap.put("succeed", true);

        return retMap;
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
        List<FileVO> files = boardService.getFileList(no);
        //리스트를 json 형태로 변환
        String data = new Gson().toJson(files).replaceAll("\\\\", "-");

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("files", data);

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

    @GetMapping("/imageDisplay")
    @ResponseBody
    public ResponseEntity<byte[]> display(String filePath) {
        File file = new File(filePath);
        ResponseEntity<byte[]> image = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            image = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    @GetMapping("/fileDownload")
    public ResponseEntity<Resource> fileDownload(@RequestHeader("User-Agent") String agent, String filePath) {
        try {
            Resource resource = new FileSystemResource(filePath);

            if(!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            String fileName = FileUtil.getOriginalFileName(resource.getFilename());

            if(agent.contains("Trident")) {     //IE
                fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "");
            } else if(agent.contains("Edge")) { //Edge
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {                            //Chrome
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO-8859-1");
            }

            return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                            .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.getFile().length()))
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
                            .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    * admin
    */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}

package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.security.CustomUserDetails;
import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import com.example.board.vo.FileVO;
import com.example.board.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService implements UserDetailsService {

    @Autowired
    BoardMapper boardMapper;

    public String now() throws Exception {
        return boardMapper.now();
    }

    public void boardWrite(BoardVO boardVO) throws Exception {
        boardMapper.boardWrite(boardVO);
    }

//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validateResult = new HashMap<String, String>();
//
//        for(FieldError error : errors.getFieldErrors()) {
//            String key = String.format("valid_%s", error.getField());
//            validateResult.put(key, error.getDefaultMessage());
//        }
//
//        return validateResult;
//    }

    public int getBoardCount(Criteria criteria) throws Exception {
        return boardMapper.getBoardCount(criteria);
    }

    public List<BoardVO> getList(Criteria criteria) throws Exception{
        return boardMapper.getListWithPaging(criteria);
    }

    public BoardVO getDetailBoard(int no) throws Exception {
        return boardMapper.getDetailBoard(no);
    }

    public void boardModifyForm(int no) throws Exception {
        boardMapper.boardModifyForm(no);
    }

    @Transactional
    public void boardModify(BoardVO boardVO) throws Exception {
        boardMapper.boardModify(boardVO);
    }

    @Transactional
    public void boardDelete(int no) throws Exception {
        boardMapper.boardDelete(no);
    }

//    public UserVO userLogin(UserVO userVO) throws Exception {
//        return boardMapper.userLogin(userVO);
//    }

    @Transactional
    public void createUser(UserVO userVO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        boardMapper.createUser(userVO);
    }

    public List<String> getErrorMessage(BindingResult bindingResult) {
        List<String> message = new ArrayList<String>();

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(x -> {
                message.add(x.getDefaultMessage());
            });
        }

        return message;
    }

    public int idCheck(String id) throws Exception {
        return boardMapper.idCheck(id);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserVO userVO = boardMapper.findById(id);
        CustomUserDetails customUserDetails = new CustomUserDetails();

        if(userVO == null) {
            throw new UsernameNotFoundException(id);
        }

        customUserDetails.setUsername(userVO.getId());
        customUserDetails.setPassword(userVO.getPassword());
        customUserDetails.setName(userVO.getName());

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userVO.getRole()));

        customUserDetails.setAuthorities(authorities);

        return customUserDetails;
    }

    @Transactional
    public void viewNoUp(int no) throws Exception {
        boardMapper.viewNoUp(no);
    }

    @Transactional
    public void insertFiles(FileVO fileVO) throws Exception {
        boardMapper.insertFiles(fileVO);
    }

    public int getBoardNo() throws Exception {
        return boardMapper.getBoardNo();
    }
}

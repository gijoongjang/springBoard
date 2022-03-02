package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public String now() throws Exception {
        return boardMapper.now();
    }

    public void boardWrite(BoardVO boardVO) throws Exception {
        boardMapper.boardWrite(boardVO);
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validateResult = new HashMap<String, String>();

        for(FieldError error : errors.getFieldErrors()) {
            String key = String.format("valid_%s", error.getField());
            validateResult.put(key, error.getDefaultMessage());
        }

        return validateResult;
    }

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
}

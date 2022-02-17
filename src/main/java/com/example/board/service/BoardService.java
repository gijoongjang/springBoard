package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.HashMap;
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
}

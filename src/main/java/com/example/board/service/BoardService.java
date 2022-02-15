package com.example.board.service;

import com.example.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public String now() throws Exception {
        return boardMapper.now();
    }
}

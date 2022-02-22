package com.example.board.mapper;

import com.example.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    String now() throws Exception;

    void boardWrite(BoardVO boardVO) throws Exception;

    List<BoardVO> getBoardList() throws Exception;
}

package com.example.board.mapper;

import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    String now() throws Exception;

    void boardWrite(BoardVO boardVO) throws Exception;

    int getBoardCount(Criteria criteria) throws Exception;

    List<BoardVO> getListWithPaging(Criteria criteria);
}

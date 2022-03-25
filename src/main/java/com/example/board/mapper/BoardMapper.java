package com.example.board.mapper;

import com.example.board.vo.BoardVO;
import com.example.board.vo.Criteria;
import com.example.board.vo.FileVO;
import com.example.board.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    String now() throws Exception;

    void boardWrite(BoardVO boardVO) throws Exception;

    int getBoardCount(Criteria criteria) throws Exception;

    List<BoardVO> getListWithPaging(Criteria criteria) throws Exception;

    BoardVO getDetailBoard(int no) throws Exception;

    void boardModifyForm(int no) throws Exception;

    void boardModify(BoardVO boardVO) throws Exception;

    void boardDelete(int no) throws Exception;

//    UserVO userLogin(UserVO userVO) throws Exception;

    void createUser(UserVO userVO) throws Exception;

    int idCheck(String id) throws Exception;

    UserVO findById(String username);

    void viewNoUp(int no) throws Exception;

    void insertFiles(FileVO fileVO) throws Exception;

    int getBoardNo() throws Exception;

    List<FileVO> getFileList(int bno) throws Exception;
}

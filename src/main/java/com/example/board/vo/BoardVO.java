package com.example.board.vo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class BoardVO {
    private int no        ;
    @NotEmpty(message = "제목을 입력하세요")
    private String title  ;
    @NotEmpty(message = "내용을 입력하세요")
    private String content;
    private String writer ;
    private Date regdate  ;
    private String viewno ;
}

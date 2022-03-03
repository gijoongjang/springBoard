package com.example.board.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UserVO {
    @NotEmpty(message = "아이디를 입력하세요")
    private String id;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;
    @NotEmpty(message = "이름을 입력하세요")
    private String name;
    private Date regdate;
}

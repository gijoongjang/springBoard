package com.example.board.vo;

import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@ToString
public class FileVO {
    private int fno                 ;
    private int bno                 ;
    private String filename         ;
    private String original_filename;
    private String upload_path      ;
    private Long filesize           ;
    private String filetype         ;
    private Date createdate         ;
    private String createuser       ;
}

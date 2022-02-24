package com.example.board.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
    private int startPage;
    private int endPage;
    private boolean previous;
    private boolean next;

    private int total;
    private Criteria criteria;

    public PageDTO(Criteria criteria, int total) {
        this.criteria = criteria;
        this.total = total;

        this.endPage = (int) ((Math.ceil(criteria.getPageNum() / 10.0)) * 10);
        this.startPage = this.endPage - 9;
        int tempEnd = (int) Math.ceil((total * 1.0) / criteria.getAmount());

        if(tempEnd < this.endPage) {
            this.endPage = tempEnd;
        }

        this.previous = this.startPage > 1;
        this.next = this.endPage < tempEnd;
    }
}

package top.wgy.book.dto;

import lombok.Data;

@Data
public class BookPageQuery {
    private String title;
    private String author;
    private String category;
    private Integer pageNo = 0; // 前端通常从0开始
    private Integer pageSize = 10;
}
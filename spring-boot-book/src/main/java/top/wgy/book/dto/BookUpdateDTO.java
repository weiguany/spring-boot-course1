package top.wgy.book.dto;

import lombok.Data;

@Data
public class BookUpdateDTO {
    private String title;
    private String author;
    private String category;
    private Integer stock;
}
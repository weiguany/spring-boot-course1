package top.wgy.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BookCreateDTO {
    @NotBlank(message = "书名不能为空")
    @Size(max = 200)
    private String title;

    @Size(max = 128)
    private String author;

    @Size(max = 32)
    private String isbn;

    @Size(max = 64)
    private String category;

    private Integer stock = 0;
}
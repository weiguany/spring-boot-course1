package top.wgy.book.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("book")
public class Book {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String category;
    private Integer stock;

    /** 逻辑删除 */
    @TableLogic
    private Integer deleted;

    /** 乐观锁 */
    @Version
    private Integer version;

    /** 自动填充 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
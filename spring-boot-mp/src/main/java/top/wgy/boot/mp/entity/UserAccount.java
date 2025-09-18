package top.wgy.boot.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("user_account")
@Data
public class UserAccount {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatarUrl;
    private Integer status;


    @TableLogic
    private Integer deleted;
    private Integer version;

    @TableField
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

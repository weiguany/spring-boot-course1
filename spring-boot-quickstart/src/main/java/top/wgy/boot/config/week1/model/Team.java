package top.wgy.boot.config.week1.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class Team {
    @Value("${team.leader}")
    @NotNull
    @Length(min = 2, max = 10, message = "长度在2-10之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号码格式不正确")
    private String phone;

    @Value("${team.age}")
    @Max(5)
    @Min(1)
    private Integer age;

    @Value("${team.createDate}")
    @Past(message = "创建时间不能晚于当前时间")
    private LocalDate createDate;
}
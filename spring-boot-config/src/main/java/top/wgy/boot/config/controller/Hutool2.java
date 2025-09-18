package top.wgy.boot.config.controller;


import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.tool.Result;

@RestController
public class Hutool2 {

    @GetMapping("/date")
    public Result<String> formatCurrentDate() {
        String format = DateUtil.now();
        return Result.success(format);
    }


}
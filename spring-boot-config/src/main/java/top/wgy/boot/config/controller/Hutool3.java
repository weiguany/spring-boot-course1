package top.wgy.boot.config.controller;

import cn.hutool.core.util.DesensitizedUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.tool.Result;

@RestController
public class Hutool3{

    @GetMapping("/string/desensitize/phone")
    public Result<String> desensitizePhone(@RequestParam String phone) {
        String desensitized = DesensitizedUtil.mobilePhone(phone);
        return Result.success(desensitized);
    }
}
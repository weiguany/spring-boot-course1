package top.wgy.boot.config.controller;



import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.tool.Result;
@RestController
public class Hutool {
    @PostMapping("/encrypt")
    public Result<String> md5Encrypt(@RequestBody String content) {
        String md5 = DigestUtil.md5Hex(content);
        return Result.success(md5);
    }
}
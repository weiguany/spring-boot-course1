package top.wgy.boot.config.controller;


import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.tool.Result;

@RestController
public class Hutool1 {

    @GetMapping("/file/read")
    public Result<String> readFile(@RequestParam String filePath) {
        String content = FileUtil.readUtf8String(filePath);
        return Result.success(content);
    }
}
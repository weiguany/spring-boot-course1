package top.wgy.boot.config.controller;



import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.tool.Result;

@RestController
public class Hutool4 {

    @GetMapping("/http/call")
    public Result<String> callExternalApi() {
        String url = "https://weather.cma.cn/web/weather/58238.html";
        // 实际要去申请天气 API 的 appid 等，这里只是示例结构
        String result = HttpUtil.get(url);
        return Result.success(result);
    }
}

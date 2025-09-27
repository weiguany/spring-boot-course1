package top.wgy.boot.exception.controller;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.wgy.boot.exception.commom.Result;
import top.wgy.boot.exception.entity.User;
import top.wgy.boot.exception.service.ExceptionService;


@RestController
public class ExceptionController {
    @Resource
    private ExceptionService exceptionService;

    @GetMapping("/test/{id}")
    public Result<String> getInfo(@PathVariable int id){
        if (id == 1){
            exceptionService.unAuthorizedError();
        }else if (id == 2){
            exceptionService.systemError();
        }
        return Result.ok("请求成功");
    }

    @PostMapping("/user")
    public Result<User> createUser(@Valid @RequestBody User user){
        return Result.ok(user);
    }

}

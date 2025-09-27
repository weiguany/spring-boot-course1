//package top.wgy.boot.exception.controller;
//
//
//import jakarta.annotation.Resource;
//import jakarta.validation.Valid;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import top.wgy.boot.exception.commom.Result;
//import top.wgy.boot.exception.entity.User;
//import top.wgy.boot.exception.service.TestService;
//
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//  @Resource
//  private TestService testService;
//
//    @GetMapping("/{id}")
//    public Result<String> getInfo(@PathVariable int id){
//        if (id == 1){
//            testService.method1();
//        }else if (id == 2){
//            testService.method2();
//        }else {
//            //int n = 1/0;
//            return Result.ok("请求成功");
//        }
//        return Result.ok("请求成功");
//   }
//
//    @PostMapping("/user")
//    public Result<User> createUser(@Valid@RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
//
//        }
//        return Result.ok(user);
//    }
//}

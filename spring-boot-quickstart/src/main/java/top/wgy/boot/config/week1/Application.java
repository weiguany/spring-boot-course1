package top.wgy.boot.config.week1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        System.out.println("启动成功");
        System.out.println("http://localhost:8088/hello");
    }
}

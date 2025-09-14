package top.wgy.boot.config.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.wgy.boot.config.model.Team;

@RestController
public class TeamController {
    //添加团队信息，并添加校验，返回规范的响应信息
    @PostMapping("/team")
    public ResponseEntity<Team> addteam(@Valid @RequestBody Team team)
    {
        return ResponseEntity.ok(team);
    }
}
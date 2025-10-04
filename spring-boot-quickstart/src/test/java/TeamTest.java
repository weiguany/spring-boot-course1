package top.wgy.boot.config.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class TeamTest {
    @Resource
    private Team team;

    @Test
    void testTeam1() {
        log.info("team:{}",team);
        assertEquals("wgy",team.getLeader());

        //判断手机号格式是否正确
        assertEquals(team.getPhone().matches("^[0-9]{11}$"),true);

        //测试团队年龄是否在1-5之间
        assertEquals(team.getAge() >=1 && team.getAge() <= 5,true);

        //测试团队创建时间是否早于当前时间
        assertEquals(team.getCreateDate().isBefore(LocalDate.now()),true);
    }
    @Test
    void testTeam2() {
        assertEquals("wgy22", team.getPhone());
    }
}
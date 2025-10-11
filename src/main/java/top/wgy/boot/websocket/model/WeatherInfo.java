package top.wgy.boot.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherInfo {
    private String city;          // 城市
    private String temperature;   // 温度
    private String condition;     // 天气状况
    private String wind;          // 风力
    private String updateTime;    // 更新时间
}
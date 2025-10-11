package top.wgy.boot.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceInfo {
    private String deviceId;      // 设备ID
    private double cpuUsage;      // CPU占用率(%)
    private double memoryUsage;   // 内存占用率(%)
    private String updateTime;    // 更新时间
}
// src/main/java/top/wgy/boot/websocket/handler/DeviceMonitorWebSocketHandler.java
package top.wgy.boot.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import top.wgy.boot.websocket.model.DeviceInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DeviceMonitorWebSocketHandler implements WebSocketHandler {
    // 存储WebSocket会话
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    // 日期时间格式化器
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的设备监控WebSocket连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());
        sendMsg(session, "🎉 欢迎连接设备监控服务！每3秒推送一次设备状态");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        log.info("收到客户端消息: {}, 会话ID: {}", payload, session.getId());
        sendMsg(session, "收到消息: " + payload);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket传输错误，会话ID: {}", session.getId(), exception);
        SESSIONS.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("WebSocket连接关闭，会话ID: {}, 关闭状态: {}, 当前连接数: {}",
                session.getId(), closeStatus, SESSIONS.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 定时任务：每3秒钟推送设备监控信息
     */
    @Scheduled(fixedRate = 3000)
    public void sendPeriodicDeviceStatus() {
        if (SESSIONS.isEmpty()) {
            log.debug("当前没有活跃的设备监控WebSocket连接");
            return;
        }
        log.info("开始执行定时设备状态推送任务，当前连接数: {}", SESSIONS.size());

        // 生成模拟设备数据
        DeviceInfo deviceInfo = generateMockDeviceInfo();
        // 格式化设备信息
        String deviceStatus = formatDeviceMessage(deviceInfo);

        // 向所有连接的客户端推送消息
        SESSIONS.values().removeIf(session -> {
            try {
                if (session.isOpen()) {
                    sendMsg(session, deviceStatus);
                    return false;
                } else {
                    log.warn("发现已关闭的会话，将其移除: {}", session.getId());
                    return true;
                }
            } catch (Exception e) {
                log.error("发送消息失败，移除会话: {}", session.getId(), e);
                return true;
            }
        });
    }

    /**
     * 生成模拟设备数据
     */
    private DeviceInfo generateMockDeviceInfo() {
        // 模拟3个设备ID
        String[] deviceIds = {"server-01", "server-02", "server-03"};

        // 随机选择一个设备
        String deviceId = deviceIds[(int) (Math.random() * deviceIds.length)];

        // 生成合理范围内的CPU和内存占用率
        double cpuUsage = Math.round((5 + Math.random() * 80) * 10) / 10.0;  // 5%-85%
        double memoryUsage = Math.round((10 + Math.random() * 70) * 10) / 10.0;  // 10%-80%

        String updateTime = LocalDateTime.now().format(timeFormatter);

        return new DeviceInfo(deviceId, cpuUsage, memoryUsage, updateTime);
    }

    /**
     * 格式化设备消息
     */
    private String formatDeviceMessage(DeviceInfo device) {
        return String.format("📊 设备监控信息 [%s]%nCPU占用率: %.1f%%%n内存占用率: %.1f%%%n更新时间: %s",
                device.getDeviceId(),
                device.getCpuUsage(),
                device.getMemoryUsage(),
                device.getUpdateTime());
    }

    /**
     * 发送消息到指定的WebSocket会话
     */
    private void sendMsg(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                TextMessage textMessage = new TextMessage(message);
                session.sendMessage(textMessage);
                log.debug("消息发送成功，会话ID: {}", session.getId());
            }
        } catch (Exception e) {
            log.error("发送消息失败，会话ID: {}", session.getId(), e);
        }
    }
}
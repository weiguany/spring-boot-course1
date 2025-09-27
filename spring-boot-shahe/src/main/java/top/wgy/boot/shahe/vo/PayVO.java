package top.wgy.boot.shahe.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayVO {
    // 商户订单号 必填
    private String outTradeNo;
    // 订单名称 必填
    private String subject;
    // 付款金额 必填
    private BigDecimal totalAmount;
}

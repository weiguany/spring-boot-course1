package top.wgy.book.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockAdjustDTO {
    @NotNull(message = "调整数量不能为空")
    private Integer amount; // 正数入库，负数出库
}
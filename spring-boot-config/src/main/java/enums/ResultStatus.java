package enums;

import lombok.Getter;

@Getter
public enum ResultStatus {
    SUCCESS("发送成功"),FALL("发送失败");;

    private final String info;

    
    ResultStatus(String info) {
        this.info = info;
    }
}

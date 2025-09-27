package top.wgy.boot.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wgy.boot.mp.entity.UserAccount;

import java.util.List;

public interface UserAccountService extends IService<UserAccount> {
    /** 创建单个用户（自动加密密码）  */
    boolean createUser(UserAccount user);
    /** 创建多个用户（自动加密密码）  */
    boolean createUsers(List<UserAccount>users);
}

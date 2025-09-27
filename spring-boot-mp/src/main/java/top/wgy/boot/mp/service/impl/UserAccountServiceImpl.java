package top.wgy.boot.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wgy.boot.mp.entity.UserAccount;
import top.wgy.boot.mp.mapper.UserAccountMapper;
import top.wgy.boot.mp.service.UserAccountService;

import java.util.List;


@Service
@Transactional
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 单个用户创建
     */
    @Override
    public boolean createUser(UserAccount user) {
        processUserBeforeSave(user);
        return this.save(user);
    }

    /**
     * 批量用户创建
     */
    @Override
    public boolean createUsers(List<UserAccount> users) {
        users.forEach(this::processUserBeforeSave);
        // 1000条一批插入
        return this.saveBatch(users, 1000);
    }

    /**
     * 公共处理逻辑：密码加密 & 默认值
     */
    private void processUserBeforeSave(UserAccount user) {
        // 密码加密：如果不是BCrypt格式（$2开头），就加密
        if (user.getPassword() != null && !user.getPassword().startsWith("$2")) {
            user.setPassword(ENCODER.encode(user.getPassword()));
        }
        // 默认未删除
        if (user.getDeleted() == null) {
            user.setDeleted(0);
        }
        // 默认启用
        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        // 默认版本号
        if (user.getVersion() == null) {
            user.setVersion(0);
        }
    }
}
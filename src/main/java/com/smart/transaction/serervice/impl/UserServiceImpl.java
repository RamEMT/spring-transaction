package com.smart.transaction.serervice.impl;

import com.smart.transaction.serervice.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Emilia
 * @Since 2020.11.03 11:42
 *
 *  使用原则
 *      在service使用事务
 *      在需要的方法上使用 @Transactional
 *  注意：@Transactional 禁止使用在类上
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 开启事务
     * @return
     */
    @Transactional
    @Override
    public String add() {
        modify();
        return null;
    }

    @Override
    public String modify() {
        return null;
    }
}

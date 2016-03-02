package com.platifon.mycards.services.impl;

import com.platifon.mycards.entity.RoleEnum;
import com.platifon.mycards.entity.User;
import com.platifon.mycards.repositories.UserRepository;
import com.platifon.mycards.services.IInitService;
import com.platifon.mycards.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Сервис для инициализации приложения после старта
 *
 * @author iv - 24.01.2016
 */
@Service
@Transactional
public class InitService implements IInitService, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (GlobalUtil.isDebug()) {
            addAdminIfNeed();
        }
    }

    @Override
    public void addAdminIfNeed() {
        final String email = "paradoxfm@mail.ru";
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            User admin = new User();
            admin.setEmail("paradoxfm@mail.ru");
            admin.setLogin("iv");
            admin.setPasswordHash(passwordEncoder.encode("qwe"));
            admin.setName("Иван Гуркин");
            Set<RoleEnum> roles = new HashSet<>();
            roles.add(RoleEnum.ADMIN);
            roles.add(RoleEnum.ORGANISATION);
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }
}

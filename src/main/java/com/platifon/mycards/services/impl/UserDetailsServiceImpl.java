package com.platifon.mycards.services.impl;

import com.platifon.mycards.entity.User;
import com.platifon.mycards.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author paradoxfm - 25.01.2016
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User

        User user = userRepository.findUserByLoginWithRole(login);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + login);
        }
        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = user.getRoles().stream().map(roleEnum -> new SimpleGrantedAuthority(roleEnum.name())).collect(Collectors.toSet());

        // на основании полученныйх даных формируем объект UserDetails
        // который позволит проверить введеный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(), roles);

        return userDetails;
    }
}

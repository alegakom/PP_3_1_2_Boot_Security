package ru.kata.spring.boot_security.demo.user;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}

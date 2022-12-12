package com.example.ex_bags.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, COURIER, STOCKMAN, HR;

    @Override
    public String getAuthority() { return name(); }
}

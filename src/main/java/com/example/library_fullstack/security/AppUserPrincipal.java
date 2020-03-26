package com.example.library_fullstack.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.library_fullstack.entity.AppUser;

import java.util.Collection;

public class AppUserPrincipal implements UserDetails {

    private AppUser appUser;
    private Collection<GrantedAuthority> authorities;

    public AppUserPrincipal(AppUser appUser, Collection<GrantedAuthority> authorities) {
        this.appUser = appUser;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return appUser.getPassword();
    }


    @Override
    public String getUsername() {
        return appUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

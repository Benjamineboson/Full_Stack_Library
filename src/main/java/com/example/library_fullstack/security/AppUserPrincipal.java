package se.ecutb.thymeleaf_db_lecture.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.ecutb.thymeleaf_db_lecture.entity.AppUser;

import java.util.Collection;

public class AppUserPrincipal implements UserDetails {

    private AppUser appUser;
    private Collection<GrantedAuthority> authorities;

    public AppUserPrincipal(AppUser appUser, Collection<GrantedAuthority> authorities) {
        this.appUser = appUser;
        this.authorities = authorities;
    }

    //Måste konvertera roles till denna typen
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //Måste ha tillgång till användaren
    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    //Måste ha tillgång till användaren
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

package com.example.library_fullstack.service;

import com.example.library_fullstack.data.AppRoleRepository;
import com.example.library_fullstack.data.AppUserRepository;
import com.example.library_fullstack.entity.AppRole;
import com.example.library_fullstack.entity.AppUser;
import com.example.library_fullstack.security.AppUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {


    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser registerAppUser(String firstName, String lastName, String email, String password, LocalDate regDate, boolean isAdmin) {
        AppUser newUser = new AppUser(firstName,lastName,email,bCryptPasswordEncoder.encode(password),regDate);
        List<AppRole> appRoleList = new ArrayList<>();

        if (isAdmin){
            AppRole admin = appRoleRepository.findByRole("ADMIN").orElseThrow(IllegalArgumentException::new);
            appRoleList.add(admin);
        }

        AppRole userRole = appRoleRepository.findByRole("USER").orElseThrow(IllegalArgumentException::new);
        appRoleList.add(userRole);

        newUser.setAppRoleList(appRoleList);
        return appUserRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> optional = appUserRepository.findByEmailIgnoreCase(email);
        AppUser user = optional.orElseThrow(() -> new IllegalArgumentException("Email "+email+" could not be found"));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (AppRole role: user.getAppRoleList()){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(authority);
        }

        return new AppUserPrincipal(user,authorities);
    }
}

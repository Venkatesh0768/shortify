package org.shortify.shortifybackend.service;

import org.jspecify.annotations.Nullable;
import org.shortify.shortifybackend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;
    private String email;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String string, String email, String username, String password, List<GrantedAuthority> authority) {
    }

    public static UserDetailsImpl build( User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                user.getId().toString(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


}

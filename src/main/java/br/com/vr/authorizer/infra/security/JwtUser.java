package br.com.vr.authorizer.infra.security;

import br.com.vr.authorizer.infra.repository.mongo.enums.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -268046329085485932L;

    private String id;
    private String username;
    private String password;
    private ProfileEnum profile;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public JwtUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities, ProfileEnum profile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
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
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ProfileEnum getProfile() {
        return profile;
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile;
    }
}

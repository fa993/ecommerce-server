package com.fa993.pojos;

import com.fa993.utils.Utility;
import com.fa993.utils.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class ECommerceUser implements UserDetails {

    @Id
    @Column(name = "user_id")
    @JsonView(View.UserView.class)
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private int role;

    public ECommerceUser() {
    }

    public ECommerceUser(String id, String email, int role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRoles(int role) {
        this.role = role;
    }

    @PrePersist
    private void resolveId() {
        if(this.id == null) {
            this.id = Utility.getId();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auths = new HashSet<>();
        if(this.role == 0 || this.role == 2) {
            //BUYER
            auths.add(new SimpleGrantedAuthority("BUYER"));
        }
        if(this.role == 1 || this.role == 2) {
            auths.add(new SimpleGrantedAuthority("SELLER"));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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

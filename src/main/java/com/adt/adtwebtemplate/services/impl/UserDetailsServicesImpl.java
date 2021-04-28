package com.adt.adtwebtemplate.services.impl;

import com.adt.adtwebtemplate.entity.Role;
import com.adt.adtwebtemplate.entity.Users;
import com.adt.adtwebtemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByUsername(s);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Role role : users.getRoles()) {
                authorities.addAll(role.getOperations());
            }
            return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
        } else
            throw new UsernameNotFoundException("Username not found!");
    }
}

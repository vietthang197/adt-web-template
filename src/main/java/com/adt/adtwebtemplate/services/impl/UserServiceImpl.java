package com.adt.adtwebtemplate.services.impl;

import com.adt.adtwebtemplate.dto.DefaultResponseDTO;
import com.adt.adtwebtemplate.entity.Role;
import com.adt.adtwebtemplate.entity.Users;
import com.adt.adtwebtemplate.exception.BusinessException;
import com.adt.adtwebtemplate.repository.RoleRepository;
import com.adt.adtwebtemplate.repository.UserRepository;
import com.adt.adtwebtemplate.services.UserService;
import com.adt.adtwebtemplate.services.vm.AdminCreateUserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DefaultResponseDTO adminCreateUser(AdminCreateUserVM adminCreateUserVM) throws BusinessException {
        long countRole = roleRepository.countByNameIsIn(adminCreateUserVM.getRoles());
        if (countRole != adminCreateUserVM.getRoles().size())
            throw new BusinessException("roles invalid");

        Set<Role> roles = roleRepository.findAllByNameIsIn(adminCreateUserVM.getRoles());

        Users users = Users.builder()
                .username(adminCreateUserVM.getUsername())
                .password(passwordEncoder.encode(adminCreateUserVM.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(users);
        return new DefaultResponseDTO(1, "Tạo mới user thành công");
    }
}

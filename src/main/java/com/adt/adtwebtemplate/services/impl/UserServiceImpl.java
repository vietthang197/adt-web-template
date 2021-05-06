package com.adt.adtwebtemplate.services.impl;

import com.adt.adtwebtemplate.dto.DefaultResponseDTO;
import com.adt.adtwebtemplate.entity.Authority;
import com.adt.adtwebtemplate.entity.Users;
import com.adt.adtwebtemplate.exception.BusinessException;
import com.adt.adtwebtemplate.repository.AuthorityRespository;
import com.adt.adtwebtemplate.repository.UserRepository;
import com.adt.adtwebtemplate.services.UserService;
import com.adt.adtwebtemplate.services.vm.AdminCreateUserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private AuthorityRespository authorityRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DefaultResponseDTO adminCreateUser(AdminCreateUserVM adminCreateUserVM) throws BusinessException {
        long countRole = authorityRespository.countByNameIsIn(adminCreateUserVM.getRoles());
        if (countRole != adminCreateUserVM.getRoles().size())
            throw new BusinessException("roles invalid");

        Set<Authority> roles = authorityRespository.findAllByNameIsIn(adminCreateUserVM.getRoles());

        Users users = Users.builder()
                .username(adminCreateUserVM.getUsername())
                .password(passwordEncoder.encode(adminCreateUserVM.getPassword()))
                .authorities(roles)
                .build();
        userRepository.save(users);
        return new DefaultResponseDTO(1, "Tạo mới user thành công");
    }
}

package com.adt.adtwebtemplate.rest;

import com.adt.adtwebtemplate.dto.DefaultResponseDTO;
import com.adt.adtwebtemplate.exception.BusinessException;
import com.adt.adtwebtemplate.services.UserService;
import com.adt.adtwebtemplate.services.vm.AdminCreateUserVM;
import com.adt.adtwebtemplate.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@RestController
@RequestMapping(Constants.API_PREFIX + Constants.ADMIN_API_PREFIX + "/user")
public class UsersRestController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADT:ADMIN:CREATE:USER')")
    @PostMapping("/create")
    public DefaultResponseDTO adminCreateUser(@RequestBody @Valid AdminCreateUserVM adminCreateUserVM) throws BusinessException {
        return userService.adminCreateUser(adminCreateUserVM);
    }
}

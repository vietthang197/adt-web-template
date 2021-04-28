package com.adt.adtwebtemplate.services;

import com.adt.adtwebtemplate.dto.DefaultResponseDTO;
import com.adt.adtwebtemplate.exception.BusinessException;
import com.adt.adtwebtemplate.services.vm.AdminCreateUserVM;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */
public interface UserService {
    DefaultResponseDTO adminCreateUser(AdminCreateUserVM adminCreateUserVM) throws BusinessException;
}

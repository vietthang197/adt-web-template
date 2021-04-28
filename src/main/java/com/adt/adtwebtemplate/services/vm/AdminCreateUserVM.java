package com.adt.adtwebtemplate.services.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreateUserVM implements Serializable {

    @NotBlank(message = "{error.user.username_invalid}")
    private String username;

    @NotBlank
    private String password;

    private Set<String> roles;
}

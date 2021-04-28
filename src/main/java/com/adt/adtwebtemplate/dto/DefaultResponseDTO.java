package com.adt.adtwebtemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultResponseDTO implements Serializable {
    private int status;

    private String message;
}

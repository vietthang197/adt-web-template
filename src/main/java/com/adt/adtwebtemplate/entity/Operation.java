package com.adt.adtwebtemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Operation implements Serializable {

    @Id
    @Size(max = 50)
    private String name;

    private String description;
}

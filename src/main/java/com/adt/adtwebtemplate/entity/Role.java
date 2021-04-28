package com.adt.adtwebtemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {

    @Id
    @Size(max = 50)
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "role_operation", joinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")},
            inverseJoinColumns = {@JoinColumn(name = "operation_name", referencedColumnName = "name")})
    private Set<Operation> operations = new HashSet<>();
}
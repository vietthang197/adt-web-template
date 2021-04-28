package com.adt.adtwebtemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role implements Serializable {

    @Id
    @Size(max = 50)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_operation", joinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")},
            inverseJoinColumns = {@JoinColumn(name = "operation_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Operation> operations = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return Objects.equals(name, ((Role) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

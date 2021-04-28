package com.adt.adtwebtemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Operation implements GrantedAuthority, Serializable {

    @Id
    @Size(max = 50)
    private String name;

    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return Objects.equals(name, ((Operation) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

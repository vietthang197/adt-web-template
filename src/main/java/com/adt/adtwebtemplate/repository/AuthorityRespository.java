package com.adt.adtwebtemplate.repository;

import com.adt.adtwebtemplate.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */
@Repository
public interface AuthorityRespository extends JpaRepository<Authority, String> {

    long countByNameIsIn(Set<String> names);

    Set<Authority> findAllByNameIsIn(Set<String> names);
}

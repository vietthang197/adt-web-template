package com.adt.adtwebtemplate.repository;

import com.adt.adtwebtemplate.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}

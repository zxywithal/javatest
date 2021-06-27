package com.pkslow.rest.repo;

import com.pkslow.rest.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Integer> {
}
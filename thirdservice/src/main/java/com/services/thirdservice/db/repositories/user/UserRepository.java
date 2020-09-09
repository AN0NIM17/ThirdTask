package com.services.thirdservice.db.repositories.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.services.thirdservice.db.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

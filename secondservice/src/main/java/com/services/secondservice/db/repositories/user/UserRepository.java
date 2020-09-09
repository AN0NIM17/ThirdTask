package com.services.secondservice.db.repositories.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.services.secondservice.db.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

package com.services.firstservice.db.repositories.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.services.firstservice.db.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}

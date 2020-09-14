package com.services.firstservice.db.repositories.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.services.firstservice.db.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying(flushAutomatically = true)
    @Transactional(readOnly = false)
    @Query(value = "UPDATE user u SET u.last_name=:lastname WHERE u.id=:id", nativeQuery = true)
    void updateLastNameById(@Param("lastname") String lastName,
            @Param("id") Integer id);
}

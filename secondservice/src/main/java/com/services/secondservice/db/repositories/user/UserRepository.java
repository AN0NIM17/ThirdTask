package com.services.secondservice.db.repositories.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.services.secondservice.db.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying(flushAutomatically = true)
    @Transactional(readOnly = false)
    @Query(value = "UPDATE user u SET u.middle_name=:middlename WHERE u.id=:id", nativeQuery = true)
    void updateMiddleNameById(@Param("middlename") String middleName,
            @Param("id") Integer id);
}

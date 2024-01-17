package com.pinterestclonebackend.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.pinterestclonebackend.demo.dto.projection.UserProjection;
import com.pinterestclonebackend.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail);

    @Modifying
    @Query(nativeQuery = true, value = "update user set last_login = CURDATE() where user_id = :userId")
    void updateLastLogin(@Param("userId") Long userId);


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<User> findByUserId(Long userId);


    @Query(nativeQuery = true, value = "select us.user_id as userId, Concat(us.user_first_name, ' ', us.user_last_name) as fullName \n" +
                                        ",us.user_email as userEmail, us.user_role as userRole \n" +
                                        " from user as us \n" +
                                            "where us.user_id = :userId")
    Optional<UserProjection> findFirstInfoUser(@Param("userId") Long userId);
}

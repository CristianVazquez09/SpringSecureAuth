package com.wolfpack.repo;

import com.wolfpack.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ISignUpRepo extends IGenericRepo<User,Integer> {


    @Modifying
    @Query(value = "INSERT INTO user_data (username, password) VALUES (:username, :password)",  nativeQuery = true)
    void insertUser(@Param("username") String username, @Param("password") String password);



    @Modifying
    @Query(value = "INSERT INTO user_role (id_user, id_role) " +
            "SELECT u.id_user, r.id_role FROM user_data u, role r " +
            "WHERE u.username = :username AND r.name = 'USER'", nativeQuery = true)
    void associateRoleWithUser(@Param("username") String username);


}

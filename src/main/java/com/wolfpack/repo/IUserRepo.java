package com.wolfpack.repo;

import com.wolfpack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends IGenericRepo<User, Integer> {

    User findOneByUsername(String username);
}

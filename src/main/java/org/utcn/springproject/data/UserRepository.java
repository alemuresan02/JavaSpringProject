package org.utcn.springproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utcn.springproject.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String email);

}

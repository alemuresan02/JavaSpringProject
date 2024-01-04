package org.utcn.springproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utcn.springproject.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}

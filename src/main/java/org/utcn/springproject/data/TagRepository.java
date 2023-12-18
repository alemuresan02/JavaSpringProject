package org.utcn.springproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utcn.springproject.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}

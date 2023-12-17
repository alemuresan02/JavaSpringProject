package org.utcn.springproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utcn.springproject.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}

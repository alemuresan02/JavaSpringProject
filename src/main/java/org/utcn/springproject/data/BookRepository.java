package org.utcn.springproject.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utcn.springproject.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}

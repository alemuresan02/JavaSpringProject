package org.utcn.springproject.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.utcn.springproject.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.quantity = b.quantity - 1 WHERE b.id = :bookId AND b.quantity > 0")
    void decrementBookQuantity(int bookId);
}

package com.manalese.facebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// The interface extends JpaRepository, which takes the Entity type (FacebookPost)
// and the Primary Key type (Long) as arguments.
@Repository
public interface FacebookRepository extends JpaRepository<FacebookPost, Long> {
    // All CRUD methods (save, findById, findAll, delete) are inherited automatically.

    // Optional: You could define custom finder methods here if needed, e.g.:
    // List<FacebookPost> findByAuthor(String author);
}
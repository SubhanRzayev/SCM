package com.scm.repositories;

import com.scm.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    // custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.userId = :userId ")
    List<Contact> findByUserId(@Param("userId") Long userId);

}

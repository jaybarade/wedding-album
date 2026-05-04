package com.wedding.backend.repository;

import com.wedding.backend.entity.User;
import com.wedding.backend.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {
    Optional<Wedding> findBySlug(String slug);
    List<Wedding> findByUser(User user);
    Boolean existsBySlug(String slug);
}

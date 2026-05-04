package com.wedding.backend.repository;

import com.wedding.backend.entity.Photo;
import com.wedding.backend.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByWeddingId(Long weddingId);
    List<Photo> findByWedding(Wedding wedding);
}

package com.sergio.vidiqvideos.repositories;

import com.sergio.vidiqvideos.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> findVideoByTituloContainsIgnoreCase(String titulo);
}

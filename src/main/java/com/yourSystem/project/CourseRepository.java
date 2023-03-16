package com.yourSystem.project;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDTO, Long> {
    // Metodo per salvare un corso nel database
    CourseDTO save(CourseDTO course);

    // Metodo per trovare un corso dal database usando l'ID
    Optional<CourseDTO> findById(Long id);

    // Metodo per trovare tutti i corsi nel database
    List<CourseDTO> findAll();

    // Metodo per eliminare un corso dal database usando l'ID
    void deleteById(Long id);
}

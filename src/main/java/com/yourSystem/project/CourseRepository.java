package com.yourSystem.project;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Metodo per salvare un corso nel database
    Course save(Course course);

    // Metodo per trovare un corso dal database usando l'ID
    Optional<Course> findById(Long id);

    // Metodo per trovare tutti i corsi nel database
    List<Course> findAll();

    // Metodo per eliminare un corso dal database usando l'ID
    void deleteById(Long id);
}


package ru.ikolesnikov.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ikolesnikov.testtask.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findByRequest(String request);

    List<Category> findAllByDeleted(Boolean deleted);

    Optional<Category> findByCategoryIdAndDeleted(Long categoryId, Boolean deleted);

    List<Category> findByNameContainsIgnoreCase(String name);
}
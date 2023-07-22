package com.example.onlinestore.Repo;

import com.example.onlinestore.Model.Kombucha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KombuchaRepo extends JpaRepository<Kombucha,Long> {
    Optional<Kombucha> findById(Long id);
    List<Kombucha> findByNameContainingIgnoreCase(String name);
    List<Kombucha> findBySynonymsNameContainingIgnoreCase(String synonymName);
}


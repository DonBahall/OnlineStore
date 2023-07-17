package com.example.onlinestore.Repo;

import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Model.Synonym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KombuchaRepo extends JpaRepository<Kombucha,Long> {

    List<Kombucha> findByNameContainingIgnoreCase(String name);
    List<Kombucha> findBySynonymsNameContainingIgnoreCase(String synonymName);
}


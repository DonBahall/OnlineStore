package com.example.onlinestore.Repo;

import com.example.onlinestore.Model.Kombucha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KombuchaRepo extends JpaRepository<Kombucha,Long> {
    List<Kombucha> findByNameOrCategory(String query, String category);
}

package com.example.onlinestore.Repo;

import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Model.Synonym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynonymRepo extends JpaRepository<Synonym,Long> {
    Synonym findByName(String name);
}

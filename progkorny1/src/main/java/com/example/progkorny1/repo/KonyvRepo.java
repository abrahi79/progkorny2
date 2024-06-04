package com.example.progkorny1.repo;

import com.example.progkorny1.model.Konyv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonyvRepo extends JpaRepository<Konyv, Long> {
}

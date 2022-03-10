package com.dioclass.devweek.Repository;


import com.dioclass.devweek.Entity.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
}

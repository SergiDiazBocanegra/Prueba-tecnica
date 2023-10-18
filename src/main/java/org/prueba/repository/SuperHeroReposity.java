package org.prueba.repository;

import org.prueba.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroReposity extends JpaRepository<SuperHero,Long> {

    @Query(value="SELECT * FROM tbl_superheroes WHERE LOWER(name) LIKE LOWER(CONCAT('%', ?1,'%'))" ,nativeQuery = true)
    public List<SuperHero> findByName(String name);
}

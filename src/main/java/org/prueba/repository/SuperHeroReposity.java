package org.prueba.repository;

import org.prueba.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroReposity extends JpaRepository<SuperHero,Long> {
}

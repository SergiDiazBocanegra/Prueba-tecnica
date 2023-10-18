package org.prueba.service;

import org.prueba.entity.SuperHero;

import java.util.List;

public interface SuperHeroService {

    public SuperHero getSuperHero(Long id);

    public List<SuperHero> getAll();

    public List<SuperHero> getByName(String search);

    public SuperHero updateSuperHero(SuperHero superHero);

    public SuperHero deleteSuperHero(Long id);

    public SuperHero save(SuperHero superHero);
}

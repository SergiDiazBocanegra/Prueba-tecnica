package org.prueba.service;

import org.prueba.entity.SuperHero;
import org.prueba.repository.SuperHeroReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroServiceImpl implements SuperHeroService{
    @Autowired
    private SuperHeroReposity superHeroReposity;

    @Override
    public SuperHero getSuperHero(Long id) {
        return superHeroReposity.findById(id).orElse(null);
    }

    @Override
    public List<SuperHero> getAll() {
        return superHeroReposity.findAll();
    }

    @Override
    public List<SuperHero> getByName(String search) {
        return superHeroReposity.findByName(search);
    }

    @Override
    public SuperHero updateSuperHero(SuperHero superHero) {
        SuperHero superHeroDB= getSuperHero(superHero.getId());
        if(null == superHeroDB){
            return null;
        }
        superHeroDB.setName(superHero.getName());
        superHeroDB.setDescription(superHero.getDescription());
        superHeroDB.setGender(superHero.getGender());
        superHeroDB.setSkill(superHero.getSkill());
        return superHeroReposity.save(superHeroDB);
    }

    @Override
    public SuperHero deleteSuperHero(Long id) {
        SuperHero superHero =getSuperHero(id);
        if(null == superHero){
            return null;
        }
        //continuar, añadir un campo de status a superhero
        return null;
    }
}

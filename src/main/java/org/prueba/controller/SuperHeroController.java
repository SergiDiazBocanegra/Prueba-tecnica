package org.prueba.controller;

import org.prueba.entity.SuperHero;
import org.prueba.service.SuperHeroService;
import org.prueba.config.LogExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/superheroes")
public class SuperHeroController {

    @Autowired
    SuperHeroService superHeroService;

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes = superHeroService.getAll();
        if (superHeroes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHeroes);
    }

    @LogExecutionTime
    @GetMapping(value = "/{id}")
//    @Cacheable("superhero")
    public ResponseEntity<SuperHero> getSuperHeroByID(@PathVariable("id") Long id) {
        SuperHero superHero = superHeroService.getSuperHero(id);
        if (null == superHero) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(superHero);
    }


    @LogExecutionTime
    @GetMapping(value = "/getByName")
    public ResponseEntity<List<SuperHero>> getByName(@RequestParam(name = "name", required = true) String name) {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes = superHeroService.getByName(name);
        if (superHeroes.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(superHeroes);
    }

    @LogExecutionTime
    @PutMapping(value = "/{id}")
    public ResponseEntity<SuperHero> updateSuperHero(@PathVariable("id") Long id, SuperHero superHero) {
        superHero.setId(id);
        SuperHero superHeroDB =  superHeroService.updateSuperHero(superHero);
        if (superHeroDB == null){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(superHeroDB);
    }

    @LogExecutionTime
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SuperHero> deleteSuperHero(@PathVariable("id") Long id){
        SuperHero superHeroDelete = superHeroService.deleteSuperHero(id);
        if (superHeroDelete == null){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(superHeroDelete);
    }

}

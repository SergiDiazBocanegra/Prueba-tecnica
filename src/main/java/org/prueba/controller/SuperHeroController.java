package org.prueba.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.prueba.entity.SuperHero;
import org.prueba.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/superheroes")
public class SuperHeroController {

    @Autowired
    SuperHeroService superHeroService;

    @GetMapping
    public ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes = superHeroService.getAll();
        if (superHeroes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(superHeroes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SuperHero> getSuperHeroByID(@PathVariable("id") Long id) {
        SuperHero superHero = superHeroService.getSuperHero(id);
        if (null == superHero) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(superHero);
    }

    @GetMapping(value = "/getByName")
    public ResponseEntity<List<SuperHero>> getByName(@RequestParam(name = "name", required = true) String name) {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes = superHeroService.getByName(name);
        if (superHeroes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(superHeroes);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<SuperHero> createSuperHero(@PathVariable("id") Long id, @RequestBody SuperHero superHero) {
        superHero.setId(id);
        SuperHero superHeroDB =  superHeroService.updateSuperHero(superHero);
        if (superHeroDB == null){
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.formatMessage(result));
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(superHeroDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SuperHero> deleteSuperHero(@PathVariable("id") Long id){
        SuperHero superHeroDelete = superHeroService.deleteSuperHero(id);
        if (superHeroDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(superHeroDelete);
    }

}

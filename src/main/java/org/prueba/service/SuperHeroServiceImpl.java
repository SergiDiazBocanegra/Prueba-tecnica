package org.prueba.service;

import org.prueba.repository.SuperHeroReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperHeroServiceImpl implements SuperHeroService{
    @Autowired
    private SuperHeroReposity superHeroReposity;
}

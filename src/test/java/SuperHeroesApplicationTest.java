import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prueba.SuperHeroesApplication;
import org.prueba.entity.SuperHero;
import org.prueba.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SuperHeroesApplication.class)
public class SuperHeroesApplicationTest {

    @Autowired
    private SuperHeroService superHeroService;

    @BeforeEach
    public void restoreInformation(){
        SuperHero spiderman=new SuperHero(1L,"Spiderman", "Description","Male","Spider","CREATED");
        SuperHero wonderWoman=new SuperHero(2L,"Wonder Woman", "Description","Female","Woman","CREATED");
        superHeroService.deleteSuperHero(1L);
        superHeroService.deleteSuperHero(2L);
        superHeroService.save(spiderman);
        superHeroService.save(wonderWoman);
    }

    @Test
    public void whenValidGetId_ThenReturnSuperHero(){
        SuperHero found= superHeroService.getSuperHero(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Spiderman");
    }

    @Test
    public void whenGetAll_ThenReturnAllSuperHeroes(){
        List<SuperHero> found= superHeroService.getAll();
        Assertions.assertThat(found.size()).isEqualTo(2);
    }

    @Test
    public void whenGetByName_ThenReturnAllSuperHeroesByName(){
        List<SuperHero> found= superHeroService.getByName("spi");
        Assertions.assertThat(found.size()).isEqualTo(1);
    }

    @Test
    public void whenUpdate_ThenReturnUpdatedSuperHero(){
        SuperHero superHero=new SuperHero(1L,"Wonder Woman", "Description","Female","Woman","UPDATED");
        SuperHero superHeroUpdated=superHeroService.updateSuperHero(superHero);
        Assertions.assertThat(superHeroUpdated.getStatus()).isEqualTo("UPDATED");
    }

    @Test
    public void whenDeleteByID_ThenReturnDeletedSuperHero(){
        SuperHero found= superHeroService.deleteSuperHero(1L);
        Assertions.assertThat(found.getStatus()).isEqualTo("DELETED");
    }






}

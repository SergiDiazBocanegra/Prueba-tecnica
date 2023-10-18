import org.assertj.core.api.Assertions;
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
    public void whenUpdateSuperHero_ThenReturnUpdatedSuperHero(){
        SuperHero superHero=new SuperHero(1L,"Wonder Woman", "Description","Female","Woman");
        SuperHero superHeroUpdated=superHeroService.updateSuperHero(superHero);
        superHeroService.getByName("spi");
        Assertions.assertThat(superHeroUpdated.getName()).isEqualTo("Wonder Woman");
    }





}

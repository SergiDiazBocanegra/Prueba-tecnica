import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.prueba.SuperHeroesApplication;
import org.prueba.entity.SuperHero;
import org.prueba.repository.SuperHeroReposity;
import org.prueba.service.SuperHeroService;
import org.prueba.service.SuperHeroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = SuperHeroesApplication.class)
public class SuperHeroesApplicationTest {


    //Si se quisiera hacer sin atacar la base de datos, se podría usar mock, dejo el codigo comentado
    //porque en este caso es una base de datos en memoria que se inicializa cada vez que se inicia el servidor
//    @Mock
//    private SuperHeroReposity superHeroRepository;

    @Autowired
    private SuperHeroService superHeroService;

    @BeforeEach
    public void restartInformation(){
        SuperHero spiderman=new SuperHero(1L,"Spiderman", "Description","Male","Spider","CREATED");
        SuperHero wonderWoman=new SuperHero(2L,"Wonder Woman", "Description","Female","Woman","CREATED");
        superHeroService.deleteSuperHero(1L);
        superHeroService.deleteSuperHero(2L);
        superHeroService.save(spiderman);
        superHeroService.save(wonderWoman);
    }


//luego en cada test solo habría que llamar al método del repository y hacer el assert

//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//        superHeroService =  new SuperHeroServiceImpl(superHeroRepository);
//        SuperHero spiderman =  SuperHero.builder()
//                .id(1L)
//                .name("Spiderman")
//                .description("Description")
//                .gender("MALE")
//                .skill("spider")
//                .status("CREATED").build();
//
//        SuperHero wonderWoman = SuperHero.builder()
//                .id(2L)
//                .name("Wonder Woman")
//                .description("Description")
//                .gender("FEMALE")
//                .skill("woman")
//                .status("CREATED").build();
//
//        List<SuperHero> superHeroes =new ArrayList<>();
//        superHeroes.add(spiderman);
//        superHeroes.add(wonderWoman);
//
//        Mockito.when(superHeroRepository.findAll()).thenReturn(superHeroes);
//        Mockito.when(superHeroRepository.findById(1L)).thenReturn(Optional.of(spiderman));
//        Mockito.when(superHeroRepository.findByName("man")).thenReturn(superHeroes);
//    }

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

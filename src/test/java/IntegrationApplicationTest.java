import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.prueba.SuperHeroesApplication;

import org.prueba.entity.SuperHero;
import org.prueba.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;




@SpringBootTest(classes = SuperHeroesApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();



    @Test
    public void testAuthenticateUser() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user?user=Sergi&password=1234"), HttpMethod.POST, entity, String.class);
        assertNotNull(response);
    }

    @Test
    public void testRetrieveSuperHeroes() throws Exception {
        headers.add("Authorization",getUserAuthenticated().getToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/superheroes"), HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<SuperHero> superHeroes = new ArrayList<>();
        try {
            superHeroes = objectMapper.readValue(response.getBody(), List.class);
            System.out.println(superHeroes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(superHeroes.size()).isEqualTo(2);
    }

    @Test
    public void testRetrieveSuperHeroByID() throws Exception {
        headers.add("Authorization",getUserAuthenticated().getToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/superheroes/1"), HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        SuperHero superHero = new SuperHero();
        try {
            superHero = objectMapper.readValue(response.getBody(), SuperHero.class);
            System.out.println(superHero);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(superHero.getName()).isEqualTo("Spiderman");
    }

    @Test
    public void testRetrieveSuperHeroByName() throws Exception {
        headers.add("Authorization",getUserAuthenticated().getToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/superheroes?name=man"), HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<SuperHero> superHeroes = new ArrayList<>();
        try {
            superHeroes = objectMapper.readValue(response.getBody(), List.class);
            System.out.println(superHeroes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(superHeroes.size()).isEqualTo(2);
    }

    @Test
    public void testUpdateSuperHero() throws Exception {
        headers.add("Authorization",getUserAuthenticated().getToken());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("id","1");
        body.add("name", "Spiderman");
        body.add("description", "Description");
        body.add("gender", "male");
        body.add("skill", "spider");
        body.add("status", "UPDATED");

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/superheroes/1"), HttpMethod.PUT, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        SuperHero superHero = new SuperHero();
        try {
            superHero = objectMapper.readValue(response.getBody(), SuperHero.class);
            System.out.println(superHero);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(superHero.getStatus()).isEqualTo("UPDATED");
    }


    @Test
    public void testDeleteSuperHero() throws Exception {
        headers.add("Authorization",getUserAuthenticated().getToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/superheroes/1"), HttpMethod.DELETE, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        SuperHero superHero = new SuperHero();
        try {
            superHero = objectMapper.readValue(response.getBody(), SuperHero.class);
            System.out.println(superHero);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(superHero.getStatus()).isEqualTo("DELETED");
    }


    private User getUserAuthenticated(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/user?user=Sergi&password=1234"), HttpMethod.POST, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        User user;
        try {
            user = objectMapper.readValue(response.getBody(), User.class);
            System.out.println(user);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
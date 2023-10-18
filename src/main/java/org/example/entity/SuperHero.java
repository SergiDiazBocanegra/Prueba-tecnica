package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_superheroes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SuperHero {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String gender;

    private String skill;
}

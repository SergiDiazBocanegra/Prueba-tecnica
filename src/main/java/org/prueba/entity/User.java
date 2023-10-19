package org.prueba.entity;

import lombok.*;

@Getter @Setter @Data @AllArgsConstructor @NoArgsConstructor
public class User {

    private String user;
    private String pwd;
    private String token;


}
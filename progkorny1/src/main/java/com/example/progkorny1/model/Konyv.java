package com.example.progkorny1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Konyvek")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Konyv {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    private Long azon;
    private String cim;
    private String szerzo;


}


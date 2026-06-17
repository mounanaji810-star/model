package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logiciels")
public class Logiciel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column
    private String version;

    @Column
    private String editeur;

    @Column
    private LocalDate dateLicence;

    @Column
    private Integer nombreLicences;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaterielStatus status = MaterielStatus.EN_SERVICE;

    @ManyToOne
    @JoinColumn(name = "assigne_a_id")
    private User assigneA;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
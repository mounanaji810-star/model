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
@Table(name = "materiels")
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String numeroSerie;

    @Column(nullable = false)
    private String marque;

    @Column
    private String modele;

    // ✅ بعد — بدون @Enumerated
    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaterielStatus status = MaterielStatus.DISPONIBLE;

    @Column
    private LocalDate dateAcquisition;

    @Column
    private LocalDate dateGarantie;

    // المستخدم المعين له المعدة
    @ManyToOne
    @JoinColumn(name = "assigne_a_id")
    private User assigneA;

    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
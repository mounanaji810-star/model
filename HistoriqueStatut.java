package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historique_statuts")
public class HistoriqueStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reclamation_id", nullable = false)
    private Reclamation reclamation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReclamationStatus ancienStatut;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReclamationStatus nouveauStatut;

    @ManyToOne
    @JoinColumn(name = "modifie_par_id", nullable = false)
    private User modifiePar;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
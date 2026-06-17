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
@Table(name = "historique_materiels")
public class HistoriqueMateriel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materiel_id", nullable = false)
    private Materiel materiel;

    @Column(nullable = false)
    private String action;

    @Column(length = 500)
    private String details;

    @ManyToOne
    @JoinColumn(name = "effectue_par_id")
    private User effectuePar;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
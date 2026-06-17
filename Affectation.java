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
@Table(name = "affectations")
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reclamation_id", nullable = false)
    private Reclamation reclamation;

    @ManyToOne
    @JoinColumn(name = "technicien_id", nullable = false)
    private User technicien;

    @ManyToOne
    @JoinColumn(name = "affecte_par_id", nullable = false)
    private User affectePar;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
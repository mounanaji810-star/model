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
@Table(name = "interventions")
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reclamation_id", nullable = false)
    private Reclamation reclamation;

    @ManyToOne
    @JoinColumn(name = "technicien_id", nullable = false)
    private User technicien;

    // ✅ حقول منفصلة — بدل description hack
    @Column(name = "type_panne", nullable = false)
    private String typePanne;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "piece_remplacee")
    private String pieceRemplacee;

    @Column(name = "notes", length = 1000)
    private String notes;

    // ✅ dateDebut/dateFin تلقائية — لا يكتبها المستخدم
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterventionStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
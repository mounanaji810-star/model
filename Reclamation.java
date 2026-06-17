package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reclamations")
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "sla_deadline")
    private LocalDateTime slaDeadline;

    @Column(nullable = false)
    private boolean slaBreached = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReclamationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReclamationStatus status = ReclamationStatus.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priorite priorite = Priorite.MOYENNE;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private CategorieReclamation categorie;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private User agent;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private User technicien;

    // ===================== العلاقات =====================

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentaire> commentaires;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceJointe> piecesJointes;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Affectation> affectations;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriqueStatut> historiqueStatuts;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalActivite> journalActivites;

    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Intervention> interventions;

    // ===================== Dates =====================

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ✅ الحقل الجديد
    @Column(name = "date_affectation")
    private LocalDateTime dateAffectation;

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
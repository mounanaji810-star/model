package com.example.demo.model;

public enum InterventionStatus {
    EN_ATTENTE,  // ✅ créé, pas encore démarré
    EN_COURS,    // ✅ démarré → dateDebut automatique
    TERMINEE,    // ✅ terminé → dateFin automatique
    ANNULEE
}
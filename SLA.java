package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sla")
public class SLA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Priorite priorite;

    // الوقت المسموح به بالساعات
    @Column(nullable = false)
    private Integer delaiResolutionHeures;

    @Column(nullable = false)
    private Integer delaiReponseHeures;
}
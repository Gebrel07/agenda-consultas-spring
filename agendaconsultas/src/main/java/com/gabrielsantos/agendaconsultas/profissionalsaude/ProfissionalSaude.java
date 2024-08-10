package com.gabrielsantos.agendaconsultas.profissionalsaude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profissionais")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String especialidade;

    @Column(nullable = false, unique = true)
    private String registro;
}

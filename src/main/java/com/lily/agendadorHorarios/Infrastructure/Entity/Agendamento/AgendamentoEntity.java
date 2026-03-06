package com.lily.agendadorHorarios.Infrastructure.Entity.Agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
@Table(name = "agendamento")
public class AgendamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String servico;
    private LocalDateTime dataHoraAgendamento;
    private String profissional;
    private String cliente;
    private String telefoneCliente;

    // Data no BD
    private LocalDateTime dataInsercao = LocalDateTime.now();
}


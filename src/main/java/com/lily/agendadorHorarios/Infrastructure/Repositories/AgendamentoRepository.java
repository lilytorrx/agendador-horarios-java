package com.lily.agendadorHorarios.Infrastructure.Repositories;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    AgendamentoEntity findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
}

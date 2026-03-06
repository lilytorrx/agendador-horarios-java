package com.lily.agendadorHorarios.Infrastructure.Repositories;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    AgendamentoEntity findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio,
                                                           LocalDateTime dataHoraFinal);

    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    List<AgendamentoEntity> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    AgendamentoEntity findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}


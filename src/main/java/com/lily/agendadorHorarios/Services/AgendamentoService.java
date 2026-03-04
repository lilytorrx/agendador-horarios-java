package com.lily.agendadorHorarios.Services;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity salvarAgendamento(AgendamentoEntity agendamento) {
        LocalDateTime horaAgendamento = 

        if (agendamentoRepository.existsById(agendamento.getId())) {

        }
        return agendamentoRepository.save(agendamento);
    }
}

package com.lily.agendadorHorarios.Services;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity salvarAgendamento(AgendamentoEntity agendamento) {
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1); // 1 em 1 hora

        AgendamentoEntity servicosAgendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if(Objects.isNull(servicosAgendados)) {
            return agendamentoRepository.save(agendamento);
        } else {
           throw new RuntimeException("O horário já está preenchido!");
        }
 
    }
}

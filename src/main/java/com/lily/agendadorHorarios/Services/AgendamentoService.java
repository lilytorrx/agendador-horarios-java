package com.lily.agendadorHorarios.Services;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public AgendamentoEntity buscarAgendamentosDoDia(LocalDate data) {
        LocalDateTime primeiraHora = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHora, horaFinalDia);
    }

    public AgendamentoEntity alterarAgendamento(AgendamentoEntity agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        AgendamentoEntity agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if(Objects.isNull(agenda)) {
            throw new RuntimeException("Horário não está preenchido!");
        }
        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}

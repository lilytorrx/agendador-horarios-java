package com.lily.agendadorHorarios.Services.Schedule;

import com.lily.agendadorHorarios.Infrastructure.Entity.Schedule.ScheduleEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleEntity salvarAgendamento(ScheduleEntity agendamento){

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusMinutes(1);

        ScheduleEntity agendados = scheduleRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),
                horaAgendamento, horaFim);


        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horário já está preenchido");
        }
        return scheduleRepository.save(agendamento);
    }

    @Transactional
    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente){
        scheduleRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public List<ScheduleEntity> buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

        return scheduleRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public ScheduleEntity alterarAgendamento(ScheduleEntity agendamento, String cliente, LocalDateTime dataHoraAgendamento){
        ScheduleEntity agenda = scheduleRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if(Objects.isNull(agenda)){
            throw new RuntimeException("Horário não está preenchido");
        }

        agenda.setDataHoraAgendamento(agendamento.getDataHoraAgendamento());

        return scheduleRepository.save(agenda);
    }
}

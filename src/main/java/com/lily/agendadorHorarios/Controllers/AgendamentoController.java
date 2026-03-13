package com.lily.agendadorHorarios.Controllers;

import com.lily.agendadorHorarios.Infrastructure.Entity.Schedule.ScheduleEntity;
import com.lily.agendadorHorarios.Services.Schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleEntity> salvarAgendamento(@RequestBody ScheduleEntity agendamento) {
        return ResponseEntity.accepted().body(scheduleService.salvarAgendamento(agendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento) {

        scheduleService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> buscarAgendamentosDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok().body(scheduleService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<ScheduleEntity> alterarAgendamentos(@RequestBody ScheduleEntity agendamento,
                                                              @RequestParam String cliente,
                                                              @RequestParam LocalDateTime dataHoraAgendamento) {
        return ResponseEntity.accepted().body(scheduleService.alterarAgendamento(agendamento,
                cliente, dataHoraAgendamento));
    }

}

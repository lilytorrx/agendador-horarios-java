package com.lily.agendadorHorarios.Controllers;

import com.lily.agendadorHorarios.Infrastructure.Entity.AgendamentoEntity;
import com.lily.agendadorHorarios.Services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoEntity> salvarAgendamento(@RequestBody AgendamentoEntity agendamentoEntity) {
        return ResponseEntity.ok(agendamentoService.salvarAgendamento(agendamentoEntity));
    };

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam LocalDateTime dataHoraAgendamento, @RequestParam String cliente) {
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<AgendamentoEntity> buscarAgendamentosDoDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDoDia(data));
    }

    @PutMapping
    public ResponseEntity<AgendamentoEntity> alterarAgendamentos(@RequestBody AgendamentoEntity agendamento, @RequestParam LocalDateTime dataHoraAgendamento, @RequestParam String cliente) {
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento));
    }

}

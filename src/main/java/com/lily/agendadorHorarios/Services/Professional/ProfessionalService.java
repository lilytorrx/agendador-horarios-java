package com.lily.agendadorHorarios.Services.Professional;

import com.lily.agendadorHorarios.DTOs.Professional.ProfessionalRequestDTO;
import com.lily.agendadorHorarios.DTOs.Professional.ProfessionalResponseDTO;
import com.lily.agendadorHorarios.Infrastructure.Entity.Professional.ProfessionalEntity;
import com.lily.agendadorHorarios.Infrastructure.Repositories.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalResponseDTO create(ProfessionalRequestDTO dto) {
        ProfessionalEntity professional = new ProfessionalEntity();
        professional.setName(dto.name());
        ProfessionalEntity saved = professionalRepository.save(professional);
        return toDTO(saved);
    }

    private ProfessionalResponseDTO toDTO(ProfessionalEntity entity) {
        return new ProfessionalResponseDTO(entity.getId(), entity.getName());
    }
}

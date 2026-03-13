package com.lily.agendadorHorarios.Infrastructure.Repositories;

import com.lily.agendadorHorarios.Infrastructure.Entity.Professional.ProfessionalEntity;
import com.lily.agendadorHorarios.Infrastructure.Entity.ProfessionalService.ProfessionalServiceEntity;
import com.lily.agendadorHorarios.Infrastructure.Entity.Service.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalServiceRepository extends JpaRepository<ProfessionalServiceEntity, String> {
    List<ProfessionalServiceEntity> findByProfessional(ProfessionalEntity professional);
    List<ProfessionalServiceEntity> findByService(ServiceEntity service);
}

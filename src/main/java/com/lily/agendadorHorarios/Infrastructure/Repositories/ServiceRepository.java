package com.lily.agendadorHorarios.Infrastructure.Repositories;

import com.lily.agendadorHorarios.Infrastructure.Entity.Service.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
    List<ServiceEntity> findByNameContainingIgnoreCase(String name);
}

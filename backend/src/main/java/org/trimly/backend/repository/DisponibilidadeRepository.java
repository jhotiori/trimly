package org.trimly.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trimly.backend.entity.DisponibilidadeEntity;
import org.trimly.backend.entity.enums.DiaSemana;

public interface DisponibilidadeRepository extends JpaRepository<DisponibilidadeEntity, Long> {
    List<DisponibilidadeEntity> findByDiaSemana(DiaSemana diaSemana);
}

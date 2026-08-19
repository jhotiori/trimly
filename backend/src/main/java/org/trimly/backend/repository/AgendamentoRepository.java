package org.trimly.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trimly.backend.entity.AgendamentoEntity;
import org.trimly.backend.entity.UsuarioEntity;
import org.trimly.backend.entity.enums.StatusAgendamento;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
    List<AgendamentoEntity> findByUsuario(UsuarioEntity usuario);

    List<AgendamentoEntity> findByStatus(StatusAgendamento status);

    List<AgendamentoEntity> findByStatusAndHorarioGreaterThanEqualAndHorarioLessThan(StatusAgendamento status,
            LocalDateTime inicio,
            LocalDateTime fim);

    List<AgendamentoEntity> findByHorario(LocalDateTime horario);

    List<AgendamentoEntity> findByHorarioBetween(LocalDateTime inicio, LocalDateTime fim);
}
